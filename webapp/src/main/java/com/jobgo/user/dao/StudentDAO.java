package com.jobgo.user.dao;

import com.jobgo.user.pojo.JobgoJgDbStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 描述：学生DAO
 * 创建时间: 2019-08-13 8:41
 * 修改时间: 2019-08-13 8:41
 */
public interface StudentDAO extends JpaRepository<JobgoJgDbStudent, String>, JpaSpecificationExecutor<JobgoJgDbStudent> {

    /**
     * 描述：通过用户id查询学生信息
     *
     * @param userId
     * @return
     */
    JobgoJgDbStudent findJobgoJgDbStudentByUserId(Integer userId);

    /**
     * 描述：通过学生id查询返回结果的数量
     *
     * @param stuId
     * @return
     */
    int countJobgoJgDbStudentByStuId(String stuId);
}
