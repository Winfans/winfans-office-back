package top.wffanshao.office.vo;

import lombok.Data;

/**
 * 描述：包装返回json格式的对象
 *
 * @param <T>
 * @author 杨炜帆
 * @date 2019/10/18
 */
@Data
public final class ResponseResult<T> {
    private int status;
    private String msg;
    private T data;

    public ResponseResult(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
