package com.jobgo.order.controller;


import com.jobgo.commom.vo.ResponseResult;
import com.jobgo.order.pojo.JobgoJgDbContract;
import com.jobgo.order.service.ContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：合同Controller
 * 创建时间: 2019-09-10 8:38
 * 修改时间: 2019-09-10 8:38
 */
@RestController
@Api("合同服务接口")
public class ContractController {

    @Autowired
    private ContractService contractService;

    /**
     * 描述：创建合同
     *
     * @param contract 合同对象
     * @return
     */
    @PostMapping("createContract")
    @ApiOperation(value = "创建合同接口，无返回值", notes = "创建合同")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contract", required = true, value = "合同的json对象，包括企业id、学生id、工作id"),
    })
    public ResponseEntity<ResponseResult<Void>> createContract(JobgoJgDbContract contract) {
        contractService.createContract(contract);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseResult<>(201, "合同创建成功"));
    }
}
