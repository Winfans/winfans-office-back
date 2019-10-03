package top.wffanshao.office.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.wffanshao.office.pojo.OfficeDbTeam;


/**
 * 描述：团队DAO
 */
@Repository
public interface TeamDAO extends JpaRepository<OfficeDbTeam, Integer>, JpaSpecificationExecutor<OfficeDbTeam> {

}
