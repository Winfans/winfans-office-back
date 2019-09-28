package com.jobgo.user.dao;

import com.jobgo.user.pojo.JobgoJgDbEnterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 描述：企业DAO
 * 创建时间: 2019-08-13 8:41
 * 修改时间: 2019-08-13 8:41
 */
public interface EnterpriseDAO extends JpaRepository<JobgoJgDbEnterprise, String>, JpaSpecificationExecutor<JobgoJgDbEnterprise> {

    /**
     * 描述：通过用户id查询企业信息
     *
     * @param userId
     * @return
     */
    JobgoJgDbEnterprise findJobgoJgDbEnterpriseByUserId(Integer userId);

    /**
     * 描述：通过企业id查询返回结果的数量
     *
     * @param entId
     * @return
     */
    int countJobgoJgDbEnterpriseByEntId(String entId);

}
