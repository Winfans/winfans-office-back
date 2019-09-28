package com.jobgo.upload.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "jobgo.upload")
public class UploadProperties {

    private String baseUrl; // 上传根路径
}
