package com.jobgo.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述：Pay属性类
 * 创建时间: 2019-08-28 11:17
 * 修改时间: 2019-08-28 11:17
 */
@Data
@Component
@ConfigurationProperties(prefix = "jobgo.pay")
public class PayProperties {

    /**
     * 公众账号ID
     */
    private String appId;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 生成签名的密钥
     */
    private String key;

    /**
     * 连接超时时间
     */
    private int httpConnectTimeoutMs;

    /**
     * 读取超时时间
     */
    private int httpReadTimeoutMs;

}
