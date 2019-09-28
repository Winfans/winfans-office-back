package com.jobgo.user.service;

import com.jobgo.user.pojo.JobgoJgDbSchool;
import com.jobgo.user.pojo.JobgoJgDbSchoolStatus;

/**
 * 描述：学校Service
 * 创建时间: 2019-09-07 18:00
 * 修改时间: 2019-09-07 18:00
 */
public interface SchoolService {


    /**
     * 描述：通过用户id查询学校信息
     *
     * @param userId
     * @return
     */
    JobgoJgDbSchool findSchoolByUserId(Integer userId);

    /**
     * 描述：通过学校id查询学校状态信息
     *
     * @param schId
     * @return
     */
    JobgoJgDbSchoolStatus findSchoolStatusBySchId(String schId);

    /**
     * 描述：添加学校
     *
     * @param school
     * @return
     */
    void insertSchool(JobgoJgDbSchool school);

    /**
     * 描述：添加学校状态
     *
     * @param schoolStatus
     * @return
     */
    void insertSchoolStatus(JobgoJgDbSchoolStatus schoolStatus);

    /**
     * 描述：学校认证
     * @param school
     */
    Boolean schoolAuthentication(JobgoJgDbSchool school);
}
