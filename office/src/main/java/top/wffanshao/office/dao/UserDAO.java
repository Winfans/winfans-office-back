package top.wffanshao.office.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.wffanshao.office.pojo.OfficeDbUser;


/**
 * 描述：用户DAO
 */
public interface UserDAO extends JpaRepository<OfficeDbUser, Integer>, JpaSpecificationExecutor<OfficeDbUser> {

    /**
     * 描述：根据用户名查询用户
     *
     * @param userName
     * @return
     */
    OfficeDbUser findOfficeDbUserByUserName(String userName);

    /**
     * 描述：根据用户名查询某个用户的数量
     *
     * @param userName
     * @return
     */
    int countOfficeDbUserByUserName(String userName);
}
