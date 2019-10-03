package top.wffanshao.office.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.wffanshao.office.pojo.OfficeDbUserTeam;
import top.wffanshao.office.pojo.OfficeDbUserTeamPK;

import java.util.List;


/**
 * 描述：用户DAO
 */
@Repository
public interface UserTeamDAO extends JpaRepository<OfficeDbUserTeam, OfficeDbUserTeamPK>, JpaSpecificationExecutor<OfficeDbUserTeam> {

    /**
     * 描述：根据用户id查询所有团队
     * @param userId
     * @return
     */
    List<OfficeDbUserTeam> findAllByUserId(Integer userId);
}
