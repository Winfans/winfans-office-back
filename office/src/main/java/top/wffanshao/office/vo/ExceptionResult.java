package top.wffanshao.office.vo;

import lombok.Data;
import top.wffanshao.office.enums.ExceptionEnum;

/**
 * 描述：异常返回结果
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
