package com.jobgo.order.controller;

import com.jobgo.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：通知控制器
 * 创建时间: 2019-09-14 17:33
 * 修改时间: 2019-09-14 17:33
 */
@Slf4j
@RestController
@RequestMapping("notify")
public class NotifyController {

    @Autowired
    private OrderService orderService;

    /**
     * 描述：微信支付成功回调,支付结果通知
     *
     * @param result 支付结果
     * @return
     */
    @PostMapping("pay")
    public Map<String, String> hello(@RequestBody Map<String, String> result) {

        // 处理回调
        orderService.handleNotify(result);

        log.info("[支付回调] 接收微信支付回调，结果：{}", result);

        // 收到微信支付结果通知返回参数给微信支付
        Map<String, String> msg = new HashMap<>();
        msg.put("return_code", "SUCCESS");
        msg.put("return_msg", "OK");
        return msg;

    }
}
