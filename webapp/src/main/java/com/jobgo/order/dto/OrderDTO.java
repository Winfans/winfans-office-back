package com.jobgo.order.dto;


import com.jobgo.order.pojo.JobgoJgDbOrder;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * 描述：订单DTO
 * 创建时间: 2019-08-28 11:17
 * 修改时间: 2019-08-28 11:17
 */
@Data
public class OrderDTO extends JobgoJgDbOrder {

    @NotNull
    private long orderId; // 订单id

    @NotNull
    private String entId; // 企业id

    @NotNull
    private int totalPay; // 支付金额

    @NotNull
    private String entName;  // 企业名称

    private long conId; // 合同id

    private OrderDtailDTO orderDtailDTO; // 订单详情DTO

    private OrderStatusDTO orderStatusDTO;
}
