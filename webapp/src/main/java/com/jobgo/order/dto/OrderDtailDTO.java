package com.jobgo.order.dto;


import com.jobgo.order.pojo.JobgoJgDbOrderDetail;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 描述：订单详情DTO
 * 创建时间: 2019-08-28 11:17
 * 修改时间: 2019-08-28 11:17
 */
@Data
public class OrderDtailDTO extends JobgoJgDbOrderDetail {

    private long id;

    @NotNull
    private long orderId;

    @NotNull
    private long jobId;

    @NotNull
    private String jobTitle;

    @NotNull
    private int totalPay;

    private String jobShowImg;

}
