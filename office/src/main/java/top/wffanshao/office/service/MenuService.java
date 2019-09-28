package top.wffanshao.office.service;


import top.wffanshao.office.dto.MenuDTO;
import top.wffanshao.office.pojo.OfficeDbSubmenu;

import java.util.List;

/**
 * 描述：菜单Service
 */
public interface MenuService {

    /**
     * 描述：查询所有菜单
     *
     * @return
     */
    List<MenuDTO> findAllMenu();

    /**
     * 描述：查询所有子菜单
     *
     * @return
     */
    List<OfficeDbSubmenu> findAllSubmenu();

    /**
     * 描述：根据子菜单id查询子菜单
     *
     * @return
     */
    OfficeDbSubmenu findSubmenuBySubmenuId(Integer submenuId);
}
