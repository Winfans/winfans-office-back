package com.jobgo.user.service.impl;

import com.jobgo.user.dao.StudentDAO;
import com.jobgo.user.dao.StudentStatusDAO;
import com.jobgo.user.pojo.JobgoJgDbStudent;
import com.jobgo.user.pojo.JobgoJgDbStudentStatus;
import com.jobgo.user.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 描述：学生Service实现类
 * 创建时间: 2019-09-07 18:00
 * 修改时间: 2019-09-07 18:00
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private StudentStatusDAO studentStatusDAO;

    /**
     * 描述：通过用户id查询学生信息
     *
     * @param userId
     * @return
     */
    @Override
    public JobgoJgDbStudent findStudentByUserId(Integer userId) {
        return this.studentDAO.findJobgoJgDbStudentByUserId(userId);
    }

    /**
     * 描述：通过学生id查询学生状态信息
     *
     * @param stuId
     * @return
     */
    @Override
    public JobgoJgDbStudentStatus findStudentStatusByStuId(String stuId) {
        return this.studentStatusDAO.findJobgoJgDbStudentStatusByStuId(stuId);
    }

    /**
     * 描述：添加学生
     *
     * @param student
     * @return
     */
    @Override
    public void insertStudent(JobgoJgDbStudent student) {
        studentDAO.saveAndFlush(student);
    }

    /**
     * 描述：添加学生状态
     *
     * @param studentStatus
     * @return
     */
    @Override
    public void insertStudentStatus(JobgoJgDbStudentStatus studentStatus) {
        this.studentStatusDAO.saveAndFlush(studentStatus);
    }

    /**
     * 描述：学生认证
     *
     * @param student
     */
    @Transactional
    public Boolean studentAuthentication(JobgoJgDbStudent student) {

        try {
            // 判断该学生是否已经认证,已经认证则返回null
            String stuId = student.getStuId();
            int count = this.studentDAO.countJobgoJgDbStudentByStuId(stuId);
            if (count != 0
                    && this.studentStatusDAO.findJobgoJgDbStudentStatusByStuId(stuId).getStatus() == 2) {
                return null;
            }

            // 已认证或审核中
            if (count >= 1) {
                return null;
            }

            // 添加学生
            this.insertStudent(student);

            JobgoJgDbStudentStatus studentStatus = new JobgoJgDbStudentStatus();

            // 设置提交审核时间
            studentStatus.setSubmitTime(new Timestamp(new Date().getTime()));

            // 将学生与学生状态关联起来
            studentStatus.setStuId(student.getStuId());

            // 将学生认证状态初始化为待审核
            studentStatus.setStatus(1);

            // 添加学生状态到数据库
            this.insertStudentStatus(studentStatus);

            return true;
        } catch (Exception e) {
            return false;
        }

    }


}
