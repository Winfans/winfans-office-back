package com.jobgo.order.service.impl;

import com.jobgo.auth.bo.UserInfo;
import com.jobgo.commom.enums.ExceptionEnum;
import com.jobgo.commom.exception.MyException;
import com.jobgo.commom.utils.IdWorker;
import com.jobgo.job.pojo.JobgoJgDbJob;
import com.jobgo.job.service.JobService;
import com.jobgo.order.dao.OrderDAO;
import com.jobgo.order.dao.OrderDetailDAO;
import com.jobgo.order.dao.OrderStatusDAO;
import com.jobgo.order.dto.OrderDTO;
import com.jobgo.order.dto.OrderDtailDTO;
import com.jobgo.order.dto.OrderStatusDTO;
import com.jobgo.order.enums.OrderStatusEnum;
import com.jobgo.gateway.interceptor.LoginInterceptor;
import com.jobgo.order.pojo.JobgoJgDbOrder;
import com.jobgo.order.pojo.JobgoJgDbOrderDetail;
import com.jobgo.order.pojo.JobgoJgDbOrderStatus;
import com.jobgo.order.service.OrderService;
import com.jobgo.order.utils.PayHelper;
import com.jobgo.order.utils.PayState;
import com.jobgo.user.pojo.JobgoJgDbEnterprise;
import com.jobgo.user.service.EnterpriseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

/**
 * 描述：订单Service实现类
 * 创建时间: 2019-08-13 8:41
 * 修改时间: 2019-08-13 8:41
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderStatusDAO orderStatusDAO;

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private PayHelper payHelper;

    @Autowired
    private JobService jobService;

    /**
     * 描述：创建订单
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public Long createOrder(OrderDTO orderDTO) {

        // 生成orderId
        long orderId = idWorker.nextId();

        // 获取登录的用户
        UserInfo userInfo = LoginInterceptor.getLoginUser();

        // 获取用户id
        int userId = userInfo.getUserId();

        // 根据用户id获取企业信息
        JobgoJgDbEnterprise enterprise = enterpriseService.findEnterpriseByUserId(userId);

        // 初始化订单数据
        JobgoJgDbOrder order = new JobgoJgDbOrder();
        order.setOrderId(orderId);
        order.setEntId(enterprise.getEntId());
        order.setEntName(enterprise.getEntName());
        order.setTotalPay(orderDTO.getTotalPay());

        // 保存订单
        this.orderDAO.saveAndFlush(order);

        // 初始化订单状态数据
        JobgoJgDbOrderStatus orderStatus = new JobgoJgDbOrderStatus();

        // 设置订单id
        orderStatus.setOrderId(orderId);

        // 设置订单创建时间
        orderStatus.setCreatedTime(new Timestamp(new Date().getTime()));

        // 初始状态未付款：1
        orderStatus.setStatus(1);

        // 保存订单状态
        this.orderStatusDAO.saveAndFlush(orderStatus);

        // 根据工作id获取工作
        JobgoJgDbJob job = jobService.findJobByJobId(orderDTO.getOrderDtailDTO().getJobId());

        // 创建订单详情
        JobgoJgDbOrderDetail orderDetail = new JobgoJgDbOrderDetail();

        // 在订单详情中添加orderId
        orderDetail.setOrderId(orderId);

        // 在订单详情中添加jobId
        orderDetail.setJobId(job.getJobId());
        orderDetail.setJobTitle(job.getJobTitle());
        orderDetail.setTotalPay(job.getJobSalary());

        // 保存订单详情
        this.orderDetailDAO.saveAndFlush(orderDetail);

        return orderId;
    }

    /**
     * 描述：根据订单号查询订单
     *
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO findOrderByOrderId(Long orderId) {
        // 查询订单
        JobgoJgDbOrder order = orderDAO.findById(orderId).get();
        if (order.getOrderId() != 0) {
            return null;
        }
        // 查询订单详情
        JobgoJgDbOrderDetail orderDetail = orderDetailDAO.findById(orderId).get();

        // 查询订单状态
        JobgoJgDbOrderStatus orderStatus = orderStatusDAO.findById(orderId).get();

        OrderDTO orderDTO = (OrderDTO) order;
        OrderDtailDTO orderDetailDTO = (OrderDtailDTO) orderDetail;
        OrderStatusDTO orderStatusDTO = (OrderStatusDTO) orderStatus;
        orderDTO.setOrderDtailDTO(orderDetailDTO);
        orderDTO.setOrderStatusDTO(orderStatusDTO);

//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setOrderId(order.getOrderId());
//        orderDTO.setConId(order.getConId());
//        orderDTO.setEntId(order.getEntId());
//        orderDTO.setEntName(order.getEntName());
//        orderDTO.setTotalPay(order.getTotalPay());

        return orderDTO;
    }

    /**
     * 描述：根据订单id生成支付链接
     *
     * @param orderId
     * @return
     */
    @Override
    public String createPayUrl(long orderId) {

        // 查询订单
        OrderDTO orderDTO = findOrderByOrderId(orderId);
        // 判断订单状态
        int status = orderDTO.getOrderStatusDTO().getStatus();
        if (status == OrderStatusEnum.PAYED_NOT_CONFIRM.getStatus()) {
            throw new MyException(ExceptionEnum.ORDER_STATUS_ERROR);
        }
        // 支付金额
        int totalPay = orderDTO.getTotalPay();

        OrderDtailDTO orderDtailDTO = orderDTO.getOrderDtailDTO();

        String desc = orderDtailDTO.getJobTitle();

        return payHelper.createPayUrl(orderId, totalPay, desc);

    }

    /**
     * 更新订单状态
     *
     * @param id
     * @param status
     * @return
     */
    @Override
    public Boolean updateOrderStatus(Long id, Integer status) {
        return null;
    }

    /**
     * 描述：处理支付成功回调
     *
     * @param result
     */
    @Override
    public void handleNotify(Map<String, String> result) {
        // 数据校验
        payHelper.isSuccess(result);

        // 校验签名
        payHelper.isValidSign(result);

        // 校验金额
        String totalFeeStr = result.get("total_fee");
        if (StringUtils.isEmpty(totalFeeStr)) {
            throw new MyException(ExceptionEnum.INVALID_ORDER_PARAM);
        }

        // 获取结果中的金额
        int totalFee = Integer.valueOf(totalFeeStr);

        // 获取订单金额
        String outTradeNo = result.get("out_trade_no");
        Long orderId = Long.valueOf(outTradeNo);
        JobgoJgDbOrder order = orderDAO.findById(orderId).orElse(null);
        if (totalFee != order.getTotalPay()) {
            // 金额不符
            throw new MyException(ExceptionEnum.INVALID_ORDER_PARAM);
        }

        // 修改订单状态
        JobgoJgDbOrderStatus orderStatus = orderStatusDAO.findById(orderId).orElse(null);
        orderStatus.setStatus(OrderStatusEnum.PAYED_NOT_CONFIRM.getStatus());
        orderStatus.setPaymentTime(new Timestamp(new Date().getTime()));
        JobgoJgDbOrderStatus savedOrderStatus = orderStatusDAO.saveAndFlush(orderStatus);
        if (savedOrderStatus.getStatus() != OrderStatusEnum.PAYED_NOT_CONFIRM.getStatus()) {
            throw new MyException(ExceptionEnum.UPDATE_ORDER_STATUS_ERROR);
        }
        log.info("[订单回调] 订单支付成功！订单编号：{}", orderId);
    }

    /**
     * 描述：查询订单状态
     *
     * @param orderId
     * @return
     */
    @Override
    public PayState findOrderStatus(long orderId) {

        // 查询订单状态
        JobgoJgDbOrderStatus orderStatus = orderStatusDAO.findById(orderId).orElse(null);
        int status = orderStatus.getStatus();

        // 判断是否支付
        if (status != OrderStatusEnum.NOT_PAY.getStatus()) {
            // 如果已支付，真的是支付
            return PayState.SUCCESS;
        }

        // 如果未支付，但其实不一定是未支付，必须去微信查询支付状态
        return payHelper.queryPayState(orderId);
    }
}
