package top.wffanshao.office.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wffanshao.office.dao.MainMenuDAO;
import top.wffanshao.office.dao.SubmenuDAO;
import top.wffanshao.office.dto.MenuDTO;
import top.wffanshao.office.pojo.OfficeDbMainMenu;
import top.wffanshao.office.pojo.OfficeDbSubmenu;
import top.wffanshao.office.service.MenuService;

import java.util.LinkedList;
import java.util.List;

/**
 * 描述：菜单Service实现类
 */
@Service
public final class MenuServiceImpl implements MenuService {

    @Autowired
    private MainMenuDAO mainMenuDAO;

    @Autowired
    private SubmenuDAO submenuDAO;

    /**
     * 描述：查询所有菜单
     *
     * @return
     */
    @Override
    public List<MenuDTO> findAllMenu() {

        List<OfficeDbMainMenu> mainMenus = mainMenuDAO.findAll();

        List<MenuDTO> menus = new LinkedList<>();

        mainMenus.stream().forEach(mainMenu -> {
            MenuDTO menuDTO = new MenuDTO();
            menuDTO.setMainMenuId(mainMenu.getMainMenuId());
            menuDTO.setMainMenuName(mainMenu.getMainMenuName());
            menus.add(menuDTO);
        });

        menus.stream().forEach(menu -> {
            List<OfficeDbSubmenu> submenus = submenuDAO.findAllByMainMenuId(menu.getMainMenuId());
            menu.setSubmenus(submenus);
        });

        return menus;
    }

    /**
     * 描述：查询所有子菜单
     *
     * @return
     */
    @Override
    public List<OfficeDbSubmenu> findAllSubmenu() {
        return submenuDAO.findAll();
    }

    /**
     * 描述：根据子菜单id查询子菜单
     *
     * @param submenuId
     * @return
     */
    @Override
    public OfficeDbSubmenu findSubmenuBySubmenuId(Integer submenuId) {
        return submenuDAO.findBySubmenuId(submenuId);
    }
}
