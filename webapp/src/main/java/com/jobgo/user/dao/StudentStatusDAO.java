package com.jobgo.user.dao;

import com.jobgo.user.pojo.JobgoJgDbStudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 描述：学生状态DAO
 * 创建时间: 2019-09-08 8:40
 * 修改时间: 2019-09-08 8:40
 */
public interface StudentStatusDAO extends JpaRepository<JobgoJgDbStudentStatus, String>, JpaSpecificationExecutor<JobgoJgDbStudentStatus> {

    /**
     * 描述：通过学生id查询学生状态信息
     *
     * @param stuId
     * @return
     */
     JobgoJgDbStudentStatus findJobgoJgDbStudentStatusByStuId(String stuId);
}
