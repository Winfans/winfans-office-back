package com.jobgo.user.service.impl;

import com.jobgo.user.dao.EnterpriseDAO;
import com.jobgo.user.dao.EnterpriseStatusDAO;
import com.jobgo.user.pojo.JobgoJgDbEnterprise;
import com.jobgo.user.pojo.JobgoJgDbEnterpriseStatus;
import com.jobgo.user.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 描述：企业Service
 * 创建时间: 2019-09-07 18:00
 * 修改时间: 2019-09-07 18:00
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseDAO enterpriseDAO;

    @Autowired
    private EnterpriseStatusDAO enterpriseStatusDAO;

    /**
     * 描述：通过用户id查询企业信息
     *
     * @param userId
     * @return
     */
    @Override
    public JobgoJgDbEnterprise findEnterpriseByUserId(Integer userId) {
        return this.enterpriseDAO.findJobgoJgDbEnterpriseByUserId(userId);
    }

    /**
     * 描述：通过企业id查询企业状态信息
     *
     * @param entId
     * @return
     */
    @Override
    public JobgoJgDbEnterpriseStatus findEnterpriseStatusByEntId(String entId) {
        return this.enterpriseStatusDAO.findJobgoJgDbEnterpriseStatusByEntId(entId);
    }

    /**
     * 描述：添加企业
     *
     * @param enterprise
     * @return
     */
    @Override
    public void insertEnterprise(JobgoJgDbEnterprise enterprise) {
        this.enterpriseDAO.saveAndFlush(enterprise);
    }

    /**
     * 描述：添加企业状态
     *
     * @param enterpriseStatus
     * @return
     */
    @Override
    public void insertEnterpriseStatus(JobgoJgDbEnterpriseStatus enterpriseStatus) {
        this.enterpriseStatusDAO.saveAndFlush(enterpriseStatus);
    }

    /**
     * 描述：企业认证
     *
     * @param enterprise
     */
    @Override
    @Transactional
    public Boolean enterpriseAuthentication(JobgoJgDbEnterprise enterprise) {
        try {
            // 判断该企业是否已经认证,已经认证则返回null
            String entId = enterprise.getEntId();
            int count = this.enterpriseDAO.countJobgoJgDbEnterpriseByEntId(entId);
            if (count != 0
                    && this.enterpriseStatusDAO.findJobgoJgDbEnterpriseStatusByEntId(entId).getStatus() == 2) {
                return null;
            }

            // 已认证或审核中
            if (count >= 1) {
                return null;
            }

            // 添加企业
            this.insertEnterprise(enterprise);

            JobgoJgDbEnterpriseStatus enterpriseStatus = new JobgoJgDbEnterpriseStatus();

            // 设置提交审核时间
            enterpriseStatus.setSubmitTime(new Timestamp(new Date().getTime()));

            // 将企业与企业状态关联起来
            enterpriseStatus.setEntId(enterprise.getEntId());

            // 将企业认证状态初始化为待审核
            enterpriseStatus.setStatus(1);

            // 添加企业状态到数据库
            this.insertEnterpriseStatus(enterpriseStatus);

            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
