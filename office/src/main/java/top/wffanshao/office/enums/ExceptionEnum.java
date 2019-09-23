package top.wffanshao.office.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 描述：异常枚举
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ExceptionEnum {

    MENU_NOT_FOUND(404, "菜单不存在"),
    SUBMENU_NOT_FOUND(404, "子菜单不存在");


    private int code;
    private String msg;
}
