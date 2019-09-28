package com.jobgo.commom.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 描述：包装返回标准json api对象
 * 创建时间: 2019-08-13 8:41
 * 修改时间: 2019-08-13 8:41
 *
 * @param <T>
 */
@Data
@AllArgsConstructor
public class ResponseResult<T> {

    private int status;
    private String msg;
    private T data;

    public ResponseResult(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

}
