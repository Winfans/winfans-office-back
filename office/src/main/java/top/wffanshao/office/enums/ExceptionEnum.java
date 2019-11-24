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


    /**
     *
     */
    PASSWORD_ERROR(400, "密码错误"),
    USERNAME_CANNOT_USED(400, "用户名不可用"),
    NO_AUTHENTICATION(400,"未授权"),
    USER_VERIFY_FAIL(400, "用户验证失败"),
    USER_REGISTER_FAIL(400, "用户注册失败"),
    USER_DELETE_FAIL(500, "用户删除失败"),
    USER_UPDATE_FAIL(500, "用户删除失败"),
    GET_USER_FAIL(500, "查询用户失败"),
    PARSE_USER_TOKEN_FAIL(500, "解析用户token失败"),
    USER_NOT_FOUND(404, "用户不存在"),
    PERMISSION_DENIED(403, "没有权限"),

    UPLOAD_FAIL(400, "上传失败"),

    TEAM_CREATE_FAIL(400, "团队创建失败"),
    TEAM_NOT_FOUND(404, "团队不存在"),
    TEAM_UPDATE_FAIL(400, "团队修改失败"),
    USER_TEAM_ADD_FAIL(400, "团队成员添加失败"),
    USER_TEAM_DELETE_FAIL(500, "团队成员删除失败"),
    USER_TEAM_UNION_FAIL(400, "用户和团队关联失败"),

    MENU_NOT_FOUND(404, "菜单不存在"),
    SUBMENU_NOT_FOUND(404, "子菜单不存在"),

    WRITTEN_NOT_FOUND(404, "签单记录不存在"),
    WRITTEN_DELETE_FAIL(500, "签单记录删除失败"),
    WRITTEN_ADD_FAIL(400, "签单记录添加失败"),

    CUSTOMER_NOT_FOUND(404, "客户不存在"),
    CUSTOMER_DELETE_FAIL(500, "客户删除失败"),
    CUSTOMER_UPDATE_FAIL(500, "客户删除失败"),
    CUSTOMER_ADD_FAIL(400, "客户添加失败");

    private int code;
    private String msg;
}
