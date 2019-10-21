package top.wffanshao.office.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 描述：签单记录DTO
 *
 * @author 杨炜帆
 * @date 2019/10/12
 */
@Data
public class WrittenDTO {
    private Integer customerId;
    private Integer userId;
    private String customerName;
    private String userName;
    private String detail;
    private Timestamp createTime;
    private Double money;
}
