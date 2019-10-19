package top.wffanshao.office.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.wffanshao.office.pojo.OfficeDbMainMenu;

/**
 * 描述：主菜单DAO
 *
 * @author 杨炜帆
 * @date 2019/10/12
 */
@Repository
public interface MainMenuDAO extends JpaRepository<OfficeDbMainMenu, Integer>, JpaSpecificationExecutor<OfficeDbMainMenu> {

}
