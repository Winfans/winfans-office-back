package com.jobgo.order.utils;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.jobgo.commom.enums.ExceptionEnum;
import com.jobgo.commom.exception.MyException;
import com.jobgo.order.config.PayConfig;
import com.jobgo.order.dao.OrderDAO;
import com.jobgo.order.dao.OrderStatusDAO;
import com.jobgo.order.enums.OrderStatusEnum;
import com.jobgo.order.pojo.JobgoJgDbOrder;
import com.jobgo.order.pojo.JobgoJgDbOrderStatus;
import com.jobgo.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 描述：支付助手
 * 创建时间: 2019-09-12 21:21
 * 修改时间: 2019-09-12 21:21
 */
@Slf4j
@Component
public class PayHelper {

    private WXPay wxPay;

    private final static String PAY_URL_KEY_PREFIX = "jobgo:pay:url:";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayConfig payConfig;

    @Autowired
    private OrderStatusDAO orderStatusDAO;

    @Autowired
    private OrderDAO orderDAO;

    public PayHelper(PayConfig payConfig) {

        // 真实开发时
        wxPay = new WXPay(payConfig);
        // 测试时
        // wxPay = new WXPay(payConfig, WXPayConstants.SignType.MD5, true);

    }

    /**
     * 描述： 创建支付链接
     *
     * @param orderId
     * @return
     */
    public String createPayUrl(Long orderId, Integer totalPay, String desc) {
        String key = PAY_URL_KEY_PREFIX + orderId;
        try {
            String url = redisTemplate.opsForValue().get(key);
            if (StringUtils.isNotBlank(url)) {
                return url;
            }
        } catch (Exception e) {
            log.error("[订单服务] 查询缓存付款链接异常,订单编号：{}", orderId, e);
        }

        try {

            Map<String, String> data = new HashMap<>();

            // 商品描述
            data.put("body", desc);
            // 订单号
            data.put("out_trade_no", orderId.toString());
            //货币
            data.put("fee_type", "CNY");
            //金额，单位是分
            data.put("total_fee", totalPay.toString());
            //调用微信支付的终端IP（estore商城的IP）
            data.put("spbill_create_ip", "127.0.0.1");
            //回调地址
            data.put("notify_url", "http://localhost:18005/wxpay/notify/pay");
            // 交易类型为扫码支付
            data.put("trade_type", "NATIVE");
            //商品id,使用假数据
            data.put("product_id", "1234567");

            // 利用wxPay工具，完成下单
            Map<String, String> result = this.wxPay.unifiedOrder(data);

            // 判断通信标示
            isSuccess(result);

            String url = result.get("code_url");

            try {
                // 将付款地址缓存，时间为30分钟
                this.redisTemplate.opsForValue().set(key, url, 30, TimeUnit.MINUTES);
            } catch (Exception e) {
                log.error("[订单服务] 缓存付款链接异常,订单编号：{}", orderId, e);
            }

            return url;
        } catch (Exception e) {
            log.error("[订单服务] 创建预交易订单异常，错误信息：{}", e);
            return null;
        }
    }

    /**
     * 描述：判断通信标示
     *
     * @param result
     */
    public void isSuccess(Map<String, String> result) {
        if (WXPayConstants.FAIL.equals(result.get("return_code"))) {
            log.error("[订单服务] 创建预交易订单失败，错误信息：{}", result.get("return_msg"));
            throw new MyException(ExceptionEnum.WX_PAY_ERROR);
        }
    }

    /**
     * 描述：查询订单状态
     *
     * @param orderId
     * @return
     */
    public PayState queryPayState(Long orderId) {
        try {

            Map<String, String> data = new HashMap<>();

            // 订单号
            data.put("out_trade_no", orderId.toString());

            // 查询订单状态
            Map<String, String> result = wxPay.orderQuery(data);

            // 数据校验
            isSuccess(result);

            // 校验签名
            isValidSign(result);

            // 校验金额
            String totalFeeStr = result.get("total_fee");
            if (StringUtils.isEmpty(totalFeeStr)) {
                throw new MyException(ExceptionEnum.INVALID_ORDER_PARAM);
            }

            // 获取结果中的金额
            int totalFee = Integer.valueOf(totalFeeStr);

            // 获取订单金额
            String outTradeNo = result.get("out_trade_no");
            Long orderId2 = Long.valueOf(outTradeNo);
            JobgoJgDbOrder order = orderDAO.findById(orderId2).orElse(null);
            if (totalFee != order.getTotalPay()) {
                // 金额不符
                throw new MyException(ExceptionEnum.INVALID_ORDER_PARAM);
            }
            String tradeState = result.get("trade_state");
            if (WXPayConstants.SUCCESS.equals(tradeState)) {
                // 修改订单状态
                JobgoJgDbOrderStatus orderStatus = orderStatusDAO.findById(orderId).orElse(null);
                orderStatus.setStatus(OrderStatusEnum.PAYED_NOT_CONFIRM.getStatus());
                orderStatus.setPaymentTime(new Timestamp(new Date().getTime()));
                JobgoJgDbOrderStatus savedOrderStatus = orderStatusDAO.saveAndFlush(orderStatus);
                if (savedOrderStatus.getStatus() != OrderStatusEnum.PAYED_NOT_CONFIRM.getStatus()) {
                    throw new MyException(ExceptionEnum.UPDATE_ORDER_STATUS_ERROR);
                }
                return PayState.SUCCESS;
            }

            if ("NOTPAY".equals(tradeState) || "USERPAYING".equals(tradeState)) {
                return PayState.NOT_PAY;
            }

            return PayState.FAIL;



        } catch (Exception e) {
            log.error("[订单服务] 查询订单状态异常", e);
            return PayState.NOT_PAY;
        }

    }

    /**
     * 描述：校验签名
     *
     * @param data
     */
    public void isValidSign(Map<String, String> data) {
        try {
            // 重新生成签名
            String sign1 = WXPayUtil.generateSignature(data, payConfig.getKey(), WXPayConstants.SignType.HMACSHA256);
            String sign2 = WXPayUtil.generateSignature(data, payConfig.getKey(), WXPayConstants.SignType.MD5);

            // 和传过来的签名进行比较
            String sign = data.get("sign");
            if (!StringUtils.equals(sign, sign1) && !StringUtils.equals(sign, sign2)) {
                throw new MyException(ExceptionEnum.INVALID_SIGN);
            }

        } catch (Exception e) {
            throw new MyException(ExceptionEnum.INVALID_SIGN);
        }
    }
}
