package com.jobgo.user.dao;

import com.jobgo.user.pojo.JobgoJgDbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 描述：用户DAO
 * 创建时间: 2019-08-13 8:41
 * 修改时间: 2019-08-13 8:41
 */
public interface UserDAO extends JpaRepository<JobgoJgDbUser, Integer>, JpaSpecificationExecutor<JobgoJgDbUser> {

    /**
     * 描述：根据用户手机号码查找记录数量
     *
     * @return
     */
    int countJobgoJgDbUserByUserPhone(String userPhone);

    /**
     * 描述：根据用户名查找记录数量
     *
     * @return
     */
    int countJobgoJgDbUserByUserName(String userName);

    /**
     * 描述：根据用户手机号码查找用户
     *
     * @return
     */
    JobgoJgDbUser findJobgoJgDbUsersByUserPhone(String userPhone);
}
