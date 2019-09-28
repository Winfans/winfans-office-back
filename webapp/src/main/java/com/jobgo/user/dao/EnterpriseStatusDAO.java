package com.jobgo.user.dao;

import com.jobgo.user.pojo.JobgoJgDbEnterpriseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 描述：企业状态DAO
 * 创建时间: 2019-09-08 8:40
 * 修改时间: 2019-09-08 8:40
 */
public interface EnterpriseStatusDAO extends JpaRepository<JobgoJgDbEnterpriseStatus, String>, JpaSpecificationExecutor<JobgoJgDbEnterpriseStatus> {

    /**
     * 描述：通过企业id查询企业状态信息
     *
     * @param entId
     * @return
     */
    JobgoJgDbEnterpriseStatus findJobgoJgDbEnterpriseStatusByEntId(String entId);
}
