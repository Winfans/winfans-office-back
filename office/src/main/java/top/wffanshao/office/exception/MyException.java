package top.wffanshao.office.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.wffanshao.office.enums.ExceptionEnum;

/**
 * 描述：全局异常
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class MyException extends RuntimeException{
    private ExceptionEnum exceptionEnum;
}
