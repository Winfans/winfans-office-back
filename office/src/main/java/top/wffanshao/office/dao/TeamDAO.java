package top.wffanshao.office.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.wffanshao.office.pojo.OfficeDbTeam;


/**
 * 描述：团队DAO
 */
public interface TeamDAO extends JpaRepository<OfficeDbTeam, Integer>, JpaSpecificationExecutor<OfficeDbTeam> {

}
