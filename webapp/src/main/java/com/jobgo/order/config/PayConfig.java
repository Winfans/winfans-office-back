package com.jobgo.order.config;

import com.github.wxpay.sdk.WXPayConfig;
import com.jobgo.order.properties.PayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * 描述：支付配置
 * 创建时间: 2019-09-12 21:21
 * 修改时间: 2019-09-12 21:21
 */
@Component
public class PayConfig implements WXPayConfig {

    @Autowired
    private PayProperties payProperties;

    @Override
    public String getAppID() {
        return payProperties.getAppId();
    }

    @Override
    public String getMchID() {
        return payProperties.getMchId();
    }

    @Override
    public String getKey() {
        return payProperties.getKey();
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return payProperties.getHttpConnectTimeoutMs();
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return payProperties.getHttpReadTimeoutMs();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
