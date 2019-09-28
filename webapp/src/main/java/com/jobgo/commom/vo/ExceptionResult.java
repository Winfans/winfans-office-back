package com.jobgo.commom.vo;

import com.jobgo.commom.enums.ExceptionEnum;
import lombok.Data;

/**
 * 描述：异常返回结果
 *
 * 创建时间: 2019-07-04 22:51
 * 修改时间: 2019-07-04 22:51
 */
@Data
public class ExceptionResult {
    private int status;
    private String msg;

    public ExceptionResult(ExceptionEnum em) {
        this.status = em.getCode();
        this.msg = em.getMsg();
    }
}
