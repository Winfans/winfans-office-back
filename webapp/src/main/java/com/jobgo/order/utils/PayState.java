package com.jobgo.order.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：支付状态
 * 创建时间: 2019-09-12 21:21
 * 修改时间: 2019-09-12 21:21
 */
@Getter
@AllArgsConstructor
public enum PayState {

    /**
     * 未支付0
     * 支付成功1
     * 支付失败2
     */
    NOT_PAY(0),
    SUCCESS(1),
    FAIL(2);

    int value;

}
