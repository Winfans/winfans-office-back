package top.wffanshao.office.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.wffanshao.office.pojo.OfficeDbUserRole;


/**
 * 描述：用户-角色DAO
 *
 * @author 杨炜帆
 * @date 2019/10/12
 */
public interface UserRoleDAO extends JpaRepository<OfficeDbUserRole, Integer>, JpaSpecificationExecutor<OfficeDbUserRole> {

    /**
     * 根据用户id查询用户-角色
     *
     * @param userId
     * @return
     */
    OfficeDbUserRole findByUserId(Integer userId);

    /**
     * 根据用户id删除用户-角色
     *
     * @param userId
     */
    void deleteByUserId(Integer userId);

    boolean existsByUserId(Integer userId);
}
