package com.jobgo.user.controller;

import com.jobgo.commom.enums.ExceptionEnum;
import com.jobgo.commom.exception.MyException;
import com.jobgo.commom.vo.ResponseResult;
import com.jobgo.user.pojo.JobgoJgDbEnterprise;
import com.jobgo.user.pojo.JobgoJgDbEnterpriseStatus;
import com.jobgo.user.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：企业Controller
 * 创建时间: 2019-09-07 18:00
 * 修改时间: 2019-09-07 18:00
 */
@RestController
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * 描述：通过用户id查询企业信息
     *
     * @param userId
     * @return
     */
    @GetMapping("findEnterpriseByUserId")
    public ResponseEntity<ResponseResult<JobgoJgDbEnterprise>> findEnterpriseByUserId(@RequestParam("userId") Integer userId) {

        JobgoJgDbEnterprise enterprise = this.enterpriseService.findEnterpriseByUserId(userId);
        if (enterprise == null) {
            throw new MyException(ExceptionEnum.ENTERPRISE_NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", enterprise));
    }


    /**
     * 描述：通过企业id查询企业状态信息
     *
     * @param entId
     * @return
     */
    @GetMapping("findEnterpriseStatusByEntId")
    public ResponseEntity<ResponseResult<JobgoJgDbEnterpriseStatus>> findEnterpriseStatusByEntId(@RequestParam("entId") String entId) {

        JobgoJgDbEnterpriseStatus enterpriseStatus = this.enterpriseService.findEnterpriseStatusByEntId(entId);
        if (enterpriseStatus == null) {
            throw new MyException(ExceptionEnum.ENTERPRISE_NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", enterpriseStatus));
    }

    /**
     * 描述：企业认证
     *
     * @param enterprise
     */
    @PostMapping("enterpriseAuthentication")
    public ResponseEntity<ResponseResult<Void>> enterpriseAuthentication(JobgoJgDbEnterprise enterprise) {


        // 提交审核并返回响应结果
        Boolean flag = this.enterpriseService.enterpriseAuthentication(enterprise);

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
