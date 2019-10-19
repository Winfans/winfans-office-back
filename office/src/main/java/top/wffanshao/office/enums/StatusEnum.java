package top.wffanshao.office.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 描述：状态枚举
 *
 * @author 杨炜帆
 * @date 2019/10/12
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum StatusEnum {

    /**
     * 无效
     */
    INVALID(0, "无效"),

    /**
     * 有效
     */
    VALID(1, "有效");
    private int code;
    private String msg;
}
