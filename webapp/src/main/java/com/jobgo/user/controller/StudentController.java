package com.jobgo.user.controller;

import com.jobgo.commom.enums.ExceptionEnum;
import com.jobgo.commom.exception.MyException;
import com.jobgo.commom.vo.ResponseResult;
import com.jobgo.user.pojo.JobgoJgDbStudent;
import com.jobgo.user.pojo.JobgoJgDbStudentStatus;
import com.jobgo.user.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：学生Controller
 * 创建时间: 2019-09-07 18:00
 * 修改时间: 2019-09-07 18:00
 */
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 描述：通过用户id查询学生信息
     *
     * @param userId
     * @return
     */
    @GetMapping("findStudentByUserId")
    public ResponseEntity<ResponseResult<JobgoJgDbStudent>> findStudentByUserId(@RequestParam("userId") Integer userId) {

        JobgoJgDbStudent student = this.studentService.findStudentByUserId(userId);
        if (student == null) {
            throw new MyException(ExceptionEnum.STUDENT_NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", student));
    }

    /**
     * 描述：通过学生id查询学生状态信息
     *
     * @param stuId
     * @return
     */
    @GetMapping("findStudentStatusByStuId")
    public ResponseEntity<ResponseResult<JobgoJgDbStudentStatus>> findStudentStatusByStuId(@RequestParam("stuId") String stuId) {

        JobgoJgDbStudentStatus studentStatus = this.studentService.findStudentStatusByStuId(stuId);
        if (studentStatus == null) {
            throw new MyException(ExceptionEnum.STUDENT_NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", studentStatus));
    }

    /**
     * 描述：学生认证
     *
     * @param student
     */
    @PostMapping("studentAuthentication")
    public ResponseEntity<ResponseResult<Void>> studentAuthentication(JobgoJgDbStudent student) {

        // 提交审核并返回响应结果
        Boolean flag = this.studentService.studentAuthentication(student);

        // 已认证或审核中
        if (flag == null) {
            throw new MyException(ExceptionEnum.AUTHENTICATED_OR_AUTHENTICATING);
        }

        // 提交失败
        if (!flag) {
            throw new MyException(ExceptionEnum.SUBMIT_FAIL);
        }

        return ResponseEntity.ok(new ResponseResult<>(201, "提交成功"));
    }



}
