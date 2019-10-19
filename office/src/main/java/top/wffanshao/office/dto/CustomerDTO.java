package top.wffanshao.office.dto;


import lombok.Data;

import java.sql.Timestamp;

/**
 * 描述：用户DTO
 *
 * @author 杨炜帆
 * @date 2019/10/18
 */
@Data
public class CustomerDTO {
    private int customerId;
    private String customerName;
    private String userName;
    private Integer status;
    private Timestamp createTime;
    private String creator;
}
