package com.jobgo.order.dto;


import com.jobgo.order.pojo.JobgoJgDbOrderStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 描述：订单状态DTO
 * 创建时间: 2019-08-28 11:17
 * 修改时间: 2019-08-28 11:17
 */
@Data
public class OrderStatusDTO extends JobgoJgDbOrderStatus {

    @NotNull
    private long orderId;
    @NotNull
    private int status;

    private Timestamp createdTime;
    private Timestamp paymentTime;
    private Timestamp endTime;
    private Timestamp closeTime;
}
