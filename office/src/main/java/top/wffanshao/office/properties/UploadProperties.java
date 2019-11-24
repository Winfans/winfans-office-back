package top.wffanshao.office.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 描述：上传属性
 *
 * @author 杨炜帆
 * @date 2019/11/24
 */
@Data
@Component
@ConfigurationProperties(prefix = "office.upload")
public class UploadProperties {

    /**
     * 上传根路径
     */
    private String baseUrl;
}
