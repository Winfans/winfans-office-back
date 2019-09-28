package com.jobgo.commom.handle;

import com.jobgo.commom.exception.MyException;
import com.jobgo.commom.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 描述：统一异常处理器
 *
 * 创建时间: 2019-07-04 22:49
 * 修改时间: 2019-07-04 22:49
 */
@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(MyException.class)
    public ResponseEntity<ExceptionResult> handlerException(MyException e) {
        return ResponseEntity.status(e.getExceptionEnum().getCode()).body(new ExceptionResult(e.getExceptionEnum()));
    }
}
