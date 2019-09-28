package top.wffanshao.office.dto;


import lombok.Data;
import top.wffanshao.office.pojo.OfficeDbSubmenu;

import java.util.List;

/**
 * 描述：菜单DTO
 */
@Data
public final class MenuDTO {

    private int mainMenuId;
    private String mainMenuName;
    private List<OfficeDbSubmenu> submenus;
}
