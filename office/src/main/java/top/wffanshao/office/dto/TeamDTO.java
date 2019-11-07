package top.wffanshao.office.dto;


import lombok.Data;

import java.util.List;

/**
 * 描述：团队DTO
 *
 * @author 杨炜帆
 * @date 2019/10/17
 */
@Data
public class TeamDTO {

    /**
     * 团队id
     */
    private Integer teamId;

    /**
     * 团队名称
     */
    private String teamName;

    /**
     *
     * 创建团队的菜单图标背景颜色
     */
    private String submenuIconBgColor;

    /**
     * 创建团队的菜单图标名称
     */
    private String submenuName;

    /**
     * 团队的家路径
     */
    private String homePath;

    /**
     * 子路径
     */
    private String subPath;

    /**
     * 团队成员
     */
    private List<UserDTO> userDtoList;





}
