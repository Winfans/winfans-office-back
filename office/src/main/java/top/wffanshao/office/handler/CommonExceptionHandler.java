package top.wffanshao.office.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import top.wffanshao.office.exception.MyException;
import top.wffanshao.office.vo.ExceptionResult;

/**
 * 描述：统一异常处理器
 */
@ControllerAdvice
public final class CommonExceptionHandler {
    @ExceptionHandler(MyException.class)
    public ResponseEntity<ExceptionResult> handlerException(MyException e) {
        return ResponseEntity.status(e.getExceptionEnum().getCode()).body(new ExceptionResult(e.getExceptionEnum()));
    }
}
