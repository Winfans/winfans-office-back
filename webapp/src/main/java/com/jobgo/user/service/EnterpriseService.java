package com.jobgo.user.service;

import com.jobgo.user.pojo.JobgoJgDbEnterprise;
import com.jobgo.user.pojo.JobgoJgDbEnterpriseStatus;

/**
 * 描述：企业Service
 * 创建时间: 2019-09-07 18:00
 * 修改时间: 2019-09-07 18:00
 */
public interface EnterpriseService {

    /**
     * 描述：通过用户id查询企业信息
     * @param userId
     * @return
     */
    JobgoJgDbEnterprise findEnterpriseByUserId(Integer userId);

    /**
     * 描述：通过企业id查询企业状态信息
     *
     * @param entId
     * @return
     */
    JobgoJgDbEnterpriseStatus findEnterpriseStatusByEntId(String entId);

    /**
     * 描述：添加企业
     *
     * @param enterprise
     * @return
     */
    void insertEnterprise(JobgoJgDbEnterprise enterprise);

    /**
     * 描述：添加企业状态
     *
     * @param enterpriseStatus
     * @return
     */
    void insertEnterpriseStatus(JobgoJgDbEnterpriseStatus enterpriseStatus);

    /**
     * 描述：企业认证
     * @param enterprise
     */
    Boolean enterpriseAuthentication(JobgoJgDbEnterprise enterprise);
}
