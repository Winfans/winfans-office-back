package com.jobgo.commom.exception;


import com.jobgo.commom.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 描述：自定义异常
 *
 * 创建时间: 2019-07-04 22:41
 * 修改时间: 2019-07-04 22:41
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyException extends RuntimeException{
    private ExceptionEnum exceptionEnum;
}


