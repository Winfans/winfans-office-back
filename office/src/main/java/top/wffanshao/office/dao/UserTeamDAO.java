package top.wffanshao.office.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.wffanshao.office.pojo.OfficeDbUserTeam;
import top.wffanshao.office.pojo.OfficeDbUserTeamPK;

import java.util.List;


/**
 * 描述：用户DAO
 *
 * @author 杨炜帆
 * @date 2019/10/12
 */
@Repository
public interface UserTeamDAO extends JpaRepository<OfficeDbUserTeam, OfficeDbUserTeamPK>, JpaSpecificationExecutor<OfficeDbUserTeam> {

    /**
     * 描述：根据用户id查询所有团队
     *
     * @param userId
     * @return
     */
    List<OfficeDbUserTeam> findAllByUserId(Integer userId);

    /**
     * 描述：根据用户id和团队id查看团队成员
     *
     * @param userId
     * @param teamId
     * @return
     */
    OfficeDbUserTeam findByUserIdAndTeamId(Integer userId, Integer teamId);


    /**
     * 根据用户id删除团队成员
     *
     * @param userId
     */
    void deleteByUserId(Integer userId);

    boolean existsByUserId(Integer userId);

    /**
     * 根据团队id查询所有团队成员
     *
     * @param teamId
     * @return
     */
    List<OfficeDbUserTeam> findAllByTeamId(Integer teamId);
}
