package com.jobgo.user.service;

import com.jobgo.user.pojo.JobgoJgDbStudent;
import com.jobgo.user.pojo.JobgoJgDbStudentStatus;

/**
 * 描述：学生Service
 * 创建时间: 2019-09-07 18:00
 * 修改时间: 2019-09-07 18:00
 */
public interface StudentService {

    /**
     * 描述：通过用户id查询学校信息
     *
     * @param userId
     * @return
     */
    JobgoJgDbStudent findStudentByUserId(Integer userId);

    /**
     * 描述：通过学生id查询学生状态信息
     *
     * @param stuId
     * @return
     */
    JobgoJgDbStudentStatus findStudentStatusByStuId(String stuId);

    /**
     * 描述：添加学生
     *
     * @param student
     * @return
     */
    void insertStudent(JobgoJgDbStudent student);

    /**
     * 描述：添加学生状态
     *
     * @param studentStatus
     * @return
     */
    void insertStudentStatus(JobgoJgDbStudentStatus studentStatus);

    /**
     * 描述：学生认证
     *
     * @param student
     */
    Boolean studentAuthentication(JobgoJgDbStudent student);
}
