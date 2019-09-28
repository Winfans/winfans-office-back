package com.jobgo.order.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：订单状态enum
 * 创建时间: 2019-09-12 21:21
 * 修改时间: 2019-09-12 21:21
 */
@Getter
@AllArgsConstructor
public enum  OrderStatusEnum {
    NOT_PAY(1),
    PAYED_NOT_CONFIRM(2),
    CONFIRMED_TRADE_SUCCESS(3),
    TRADE_CLOSE(4);
    private int status;
}
