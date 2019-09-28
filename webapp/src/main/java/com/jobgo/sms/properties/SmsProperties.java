package com.jobgo.sms.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述：短信服务实体类
 * 创建时间: 2019-08-16 15:31
 * 修改时间: 2019-08-16 15:31
 */
@Data
@Component
@ConfigurationProperties(prefix = "jobgo.sms")
public class SmsProperties {

    // accessKeyId
    String accessKeyId;

    // AccessKeySecret
    String accessKeySecret;

    // 签名名称
    String signName;

    // 模板名称
    String templateCode;
}
