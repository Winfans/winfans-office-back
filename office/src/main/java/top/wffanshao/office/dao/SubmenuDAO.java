package top.wffanshao.office.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.wffanshao.office.pojo.OfficeDbSubmenu;

import java.util.List;

/**
 * 描述：子菜单DAO
 *
 * @author 杨炜帆
 * @date 2019/10/12
 */
@Repository
public interface SubmenuDAO extends JpaRepository<OfficeDbSubmenu, Integer>, JpaSpecificationExecutor<OfficeDbSubmenu> {

    /**
     * 描述：根据主菜单id查询所有子菜单
     *
     * @param mainMenuId
     * @return
     */
    List<OfficeDbSubmenu> findAllByMainMenuId(Integer mainMenuId);

    /**
     * 描述：根据子菜单id查询子菜单
     *
     * @param submenuId
     * @return
     */
    OfficeDbSubmenu findBySubmenuId(Integer submenuId);
}
