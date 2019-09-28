package com.jobgo.user.service.impl;

import com.jobgo.user.dao.SchoolDAO;
import com.jobgo.user.dao.SchoolStatusDAO;
import com.jobgo.user.pojo.JobgoJgDbSchool;
import com.jobgo.user.pojo.JobgoJgDbSchoolStatus;
import com.jobgo.user.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 描述：学校Service实现类
 * 创建时间: 2019-09-07 18:00
 * 修改时间: 2019-09-07 18:00
 */
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolDAO schoolDAO;

    @Autowired
    private SchoolStatusDAO schoolStatusDAO;

    /**
     * 描述：通过用户id查询学校信息
     *
     * @param userId
     * @return
     */
    @Override
    public JobgoJgDbSchool findSchoolByUserId(Integer userId) {
        return this.schoolDAO.findJobgoJgDbSchoolByUserId(userId);
    }

    /**
     * 描述：通过学校id查询学校状态信息
     *
     * @param schId
     * @return
     */
    @Override
    public JobgoJgDbSchoolStatus findSchoolStatusBySchId(String schId) {
        return this.schoolStatusDAO.findJobgoJgDbSchoolStatusBySchId(schId);
    }

    /**
     * 描述：添加学校
     *
     * @param school
     * @return
     */
    @Override
    public void insertSchool(JobgoJgDbSchool school) {
        this.schoolDAO.saveAndFlush(school);
    }

    /**
     * 描述：添加学校状态
     *
     * @param schoolStatus
     * @return
     */
    @Override
    public void insertSchoolStatus(JobgoJgDbSchoolStatus schoolStatus) {
        this.schoolStatusDAO.saveAndFlush(schoolStatus);
    }

    /**
     * 描述：学校认证
     *
     * @param school
     */
    @Override
    @Transactional
    public Boolean schoolAuthentication(JobgoJgDbSchool school) {
        try {
            // 判断该学校是否已经认证,已经认证则返回null
            String schId = school.getSchId();
            int count = this.schoolDAO.countJobgoJgDbSchoolBySchId(schId);
            if (count != 0
                    && this.schoolStatusDAO.findJobgoJgDbSchoolStatusBySchId(schId).getStatus() == 2) {
                return null;
            }

            // 已认证或审核中
            if (count >= 1) {
                return null;
            }

            // 添加学校
            this.insertSchool(school);

            JobgoJgDbSchoolStatus schoolStatus = new JobgoJgDbSchoolStatus();

            // 设置提交审核时间
            schoolStatus.setSubmitTime(new Timestamp(new Date().getTime()));

            // 将学校与学校状态关联起来
            schoolStatus.setSchId(school.getSchId());

            // 将学校认证状态初始化为待审核
            schoolStatus.setStatus(1);

            // 添加学校状态到数据库
            this.insertSchoolStatus(schoolStatus);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
