package com.jobgo.sms.listener;

import com.jobgo.commom.utils.JsonUtils;
import com.jobgo.sms.properties.SmsProperties;
import com.jobgo.sms.utils.SmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 描述：短信服务监听器
 * 创建时间: 2019-08-16 15:31
 * 修改时间: 2019-08-16 15:31
 */
@Slf4j
@Component
@EnableConfigurationProperties
public class SmsListener {

    @Autowired
    private SmsUtils smsUtils;

    @Autowired
    private SmsProperties properties;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "sms.verify.code.queue", durable = "true"),
            exchange = @Exchange(name = "jobgo.sms.exchange", type = ExchangeTypes.TOPIC),
            key = "sms.verify.code"
    ))
    public void listenSms(Map<String, String> msg) {
        if (msg == null || msg.size() <= 0) {
            // 放弃处理
            return;
        }

        String phone = msg.get("phone");
        String code = msg.get("code");


        if (StringUtils.isBlank(phone) || StringUtils.isBlank(code)) {
            // 放弃处理
            return;
        }

        // 发送消息
        this.smsUtils.sendSms(phone, properties.getSignName(), properties.getTemplateCode(), JsonUtils.serialize(code));
    }
}
