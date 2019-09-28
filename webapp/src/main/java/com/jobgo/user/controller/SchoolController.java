package com.jobgo.user.controller;

import com.jobgo.commom.enums.ExceptionEnum;
import com.jobgo.commom.exception.MyException;
import com.jobgo.commom.vo.ResponseResult;
import com.jobgo.user.pojo.JobgoJgDbSchool;
import com.jobgo.user.pojo.JobgoJgDbSchoolStatus;
import com.jobgo.user.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：学校Controller
 * 创建时间: 2019-09-07 18:00
 * 修改时间: 2019-09-07 18:00
 */
@RestController
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    /**
     * 描述：通过用户id查询学校信息
     *
     * @param userId
     * @return
     */
    @GetMapping("findSchoolByUserId")
    public ResponseEntity<ResponseResult<JobgoJgDbSchool>> findSchoolByUserId(@RequestParam("userId") Integer userId) {

        JobgoJgDbSchool school = this.schoolService.findSchoolByUserId(userId);
        if (school == null) {
            throw new MyException(ExceptionEnum.SCHOOL_NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", school));
    }


    /**
     * 描述：通过学校id查询学校状态信息
     *
     * @param schId
     * @return
     */
    @GetMapping("findSchoolStatusBySchId")
    public ResponseEntity<ResponseResult<JobgoJgDbSchoolStatus>> findSchoolStatusBySchId(@RequestParam("schId") String schId) {

        JobgoJgDbSchoolStatus schoolStatus = this.schoolService.findSchoolStatusBySchId(schId);
        if (schoolStatus == null) {
            throw new MyException(ExceptionEnum.SCHOOL_NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", schoolStatus));
    }


    /**
     * 描述：学校认证
     *
     * @param school
     */
    @PostMapping("schoolAuthentication")
    public ResponseEntity<ResponseResult<Void>> schoolAuthentication(JobgoJgDbSchool school) {

        // 提交审核并返回响应结果
        Boolean flag = this.schoolService.schoolAuthentication(school);

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
