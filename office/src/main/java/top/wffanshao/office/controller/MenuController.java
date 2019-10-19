package top.wffanshao.office.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wffanshao.office.dto.MenuDTO;
import top.wffanshao.office.enums.ExceptionEnum;
import top.wffanshao.office.exception.MyException;
import top.wffanshao.office.pojo.OfficeDbSubmenu;
import top.wffanshao.office.service.MenuService;
import top.wffanshao.office.vo.ResponseResult;

import java.util.List;

/**
 * 描述：菜单Controller
 *
 * @author 杨炜帆
 * @date 2019/10/12
 */
@RestController
@RequestMapping("menu")
public final class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 描述：查询所有菜单
     *
     * @return
     */
    @GetMapping("findAllMenu")
    public ResponseEntity<ResponseResult<List<MenuDTO>>> findAllMenu() {

        List<MenuDTO> menus = menuService.findAllMenu();

        if (CollectionUtils.isEmpty(menus)) {
            throw new MyException(ExceptionEnum.MENU_NOT_FOUND);
        }

        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", menus));
    }


    /**
     * 描述：查询所有子菜单
     *
     * @return
     */
    @GetMapping("findAllSubmenu")
    public ResponseEntity<ResponseResult<List<OfficeDbSubmenu>>> findAllSubmenu() {

        List<OfficeDbSubmenu> submenus = menuService.findAllSubmenu();

        if (CollectionUtils.isEmpty(submenus)) {
            throw new MyException(ExceptionEnum.MENU_NOT_FOUND);
        }

        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", submenus));
    }


    /**
     * 描述：根据子菜单id查询子菜单
     *
     * @return
     */
    @GetMapping("findSubmenuBySubmenuId/{submenuId}")
    public ResponseEntity<ResponseResult<OfficeDbSubmenu>> findSubmenuBySubmenuId(@PathVariable("submenuId") Integer submenuId) {

        OfficeDbSubmenu submenu = menuService.findSubmenuBySubmenuId(submenuId);

        if (submenu == null) {
            throw new MyException(ExceptionEnum.SUBMENU_NOT_FOUND);
        }

        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", submenu));
    }


}
