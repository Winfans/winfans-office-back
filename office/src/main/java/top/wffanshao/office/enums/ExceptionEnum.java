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


    PASSWORD_ERROR(400, "密码错误"),
    USERNAME_CANNOT_USED(400, "用户名不可用"),
    NO_AUTHENTICATION(400,"未授权"),
    USER_REGISTER_FAIL(400, "用户注册失败"),
    USER_VERIFY_FAIL(400, "用户验证失败"),
    TEAM_CREATE_FAIL(400, "团队创建失败"),


    MENU_NOT_FOUND(404, "菜单不存在"),
    SUBMENU_NOT_FOUND(404, "子菜单不存在");

    private int code;
    private String msg;
}
