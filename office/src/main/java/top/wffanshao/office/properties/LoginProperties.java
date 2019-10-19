package top.wffanshao.office.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author winfans
 */
@Data
@Component
@ConfigurationProperties("office.login")
public class LoginProperties {

    private String cookieName;
    private String cookieValue;
    private Integer cookieMaxAge;

}
