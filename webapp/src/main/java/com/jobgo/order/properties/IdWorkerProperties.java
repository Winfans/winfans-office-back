package com.jobgo.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述：IdWorker属性
 * 创建时间: 2019-08-31 20:18
 * 修改时间: 2019-08-31 20:18
 */
@Data
@Component
@ConfigurationProperties(prefix = "jobgo.worker")
public class IdWorkerProperties {

    /**
     * 当前机器id
     */
    private long workerId;

    /**
     * 序列号
     */
    private long dataCenterId;

}
