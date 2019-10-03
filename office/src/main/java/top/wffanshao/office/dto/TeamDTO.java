package top.wffanshao.office.dto;


import lombok.Data;

/**
 * 描述：团队DTO
 */
@Data
public class TeamDTO {

    // 团队id
    private int teamId;
    // 团队名称
    private String teamName;
    // 创建团队的菜单图标背景颜色
    private String submenuIconBgColor;
    // 创建团队的菜单图标名称
    private String submenuName;
}
