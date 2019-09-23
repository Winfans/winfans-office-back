package top.wffanshao.office.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.wffanshao.office.pojo.OfficeDbMainMenu;

/**
 * 描述：主菜单DAO
 */
public interface MainMenuDAO extends JpaRepository<OfficeDbMainMenu, Integer>, JpaSpecificationExecutor<OfficeDbMainMenu> {

}
