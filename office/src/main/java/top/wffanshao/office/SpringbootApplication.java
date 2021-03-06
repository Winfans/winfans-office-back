package top.wffanshao.office;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * nohup java -jar crm.jar >webapp-back.log 2>&1 & 部署项目
 * java -jar crm.jar 运行项目
 *
 * @author 杨炜帆
 * @date 2019/10/12
 */
@SpringBootApplication
public class SpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class);
    }
}
