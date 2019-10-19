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
public enum RoleEnum {

    /**
     * 无效
     */
    USER(1, "普通用户", "user"),

    /**
     * 有效
     */
    ADMIN(1, "管理员", "admin");

    private int roleId;
    private String roleName;
    private String roleCode;
}
