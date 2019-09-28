package com.jobgo.job.controller;


import com.jobgo.commom.enums.ExceptionEnum;
import com.jobgo.commom.exception.MyException;
import com.jobgo.commom.vo.ResponseResult;
import com.jobgo.job.pojo.JobgoJgDbJob;
import com.jobgo.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述：工作Controller
 * 创建时间: 2019-09-15 10:55
 * 修改时间: 2019-09-17 20:26
 */
@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    /**
     * 描述：发布工作提交审核
     * 状态：1、待审核 2、审核通过 3、审核未通过 4、审核通过，人数未满 5、审核通过，人数已满
     *
     * @param job
     * @return
     */
    @PostMapping("createJob")
    public ResponseEntity<ResponseResult<Boolean>> createJob(JobgoJgDbJob job) {


        // 是否发布成功
        Boolean flag = jobService.createJob(job);

        // 如果发布失败，抛出异常并返回结果
        if (!flag) {
            throw new MyException(ExceptionEnum.DELETE_JOB_FAIL);
        }

        // 发布成功
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseResult<>(200, "发布工作成功，等待审核", flag));
    }

    /**
     * 描述：查询所有企业发布的工作
     *
     * @return
     */
    @GetMapping("findAllJob")
    public ResponseEntity<ResponseResult<List<JobgoJgDbJob>>> createOrder() {

        List<JobgoJgDbJob> jobs = jobService.findAllJob();

        // 是否查询成功
        if (CollectionUtils.isEmpty(jobs)) {
            throw new MyException(ExceptionEnum.JOB_NOT_FOUND);
        }

        return ResponseEntity.ok(new ResponseResult<>(201, "查询工作成功", jobs));
    }

    /**
     * 描述：根据企业id查询该企业发布的所有工作
     *
     * @param endId
     * @return
     */
    @GetMapping("findAllJobByEntId/{endId}")
    public ResponseEntity<ResponseResult<List<JobgoJgDbJob>>> findAllJobByEntId(@PathVariable("endId") String endId) {

        List<JobgoJgDbJob> jobs = jobService.findAllJobByEntId(endId);

        // 是否查询成功
        if (CollectionUtils.isEmpty(jobs)) {
            throw new MyException(ExceptionEnum.JOB_NOT_FOUND);
        }

        return ResponseEntity.ok(new ResponseResult<>(201, "查询工作成功", jobs));
    }


    /**
     * 描述：根据jobId查询job
     *
     * @param jobId
     * @return
     */
    @GetMapping("findJobByJobId/{jobId}")
    public ResponseEntity<ResponseResult<JobgoJgDbJob>> findJobByJobId(@PathVariable("jobId") Long jobId) {

        JobgoJgDbJob job = jobService.findJobByJobId(jobId);

        // 是否查询成功
        if (job == null) {
            throw new MyException(ExceptionEnum.JOB_NOT_FOUND);
        }

        return ResponseEntity.ok(new ResponseResult<>(200, "查询工作成功", job));
    }


    /**
     * 描述：分页查询所有企业发布工作
     *
     * @param page 页数
     * @param size 显示数量
     * @return
     */
    @GetMapping("findJobByPage")
    public ResponseEntity<ResponseResult<Page<JobgoJgDbJob>>> findJobByPage(@RequestParam("page") int page,
                                                                            @RequestParam("size") int size) {

        // 从第0页开始
        Page<JobgoJgDbJob> jobPage = jobService.findJobByPage(page - 1, size);

        // 是否查询成功
        if (jobPage == null) {
            throw new MyException(ExceptionEnum.JOB_NOT_FOUND);
        }

        return ResponseEntity.ok(new ResponseResult<>(201, "查询工作成功", jobPage));
    }


    /**
     * 描述：根据企业id分页查询某个企业发布的工作
     *
     * @param entId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("findJobByEntIdAndPage/{entId}")
    public ResponseEntity<ResponseResult<Page<JobgoJgDbJob>>> findJobByEntIdAndPage(@PathVariable("entId") String entId,
                                                                                    @RequestParam("page") int page,
                                                                                    @RequestParam("size") int size) {

        // 从第0页开始
        Page<JobgoJgDbJob> jobPage = jobService.findJobByEntIdAndPage(entId, page - 1, size);

        // 是否查询成功
        if (jobPage == null) {
            throw new MyException(ExceptionEnum.JOB_NOT_FOUND);
        }

        return ResponseEntity.ok(new ResponseResult<>(201, "查询工作成功", jobPage));
    }


    /**
     * 描述：根据jobId删除job
     *
     * @param jobId
     * @return
     */
    @DeleteMapping("deleteJobByJobId/{jobId}")
    public ResponseEntity<ResponseResult<Boolean>> deleteJobByJobId(@PathVariable("jobId") Long jobId) {

        // 是否删除成功
        boolean flag = jobService.deleteJobByJobId(jobId);

        // 如果删除失败，抛出异常并返回结果
        if (!flag) {
            throw new MyException(ExceptionEnum.DELETE_JOB_FAIL);
        }

        return ResponseEntity.ok(new ResponseResult<>(200, "删除工作成功", flag));
    }



    /**
     * 描述：根据企业id删除该企业发布的所有工作
     *
     * @param entId
     * @return
     */
    @DeleteMapping("deleteAllJobByEntId/{entId}")
    public ResponseEntity<ResponseResult<Boolean>> deleteAllJobByEntId(@PathVariable("entId") String entId) {

        // 是否删除成功
        boolean flag = jobService.deleteAllJobByEntId(entId);

        // 如果删除失败，抛出异常并返回结果
        if (!flag) {
            throw new MyException(ExceptionEnum.DELETE_JOB_FAIL);
        }

        return ResponseEntity.ok(new ResponseResult<>(200, "删除工作成功", flag));
    }

    /**
     * 描述：根据jobId修改job状态
     * 状态：1、待审核 2、审核通过 3、审核未通过 4、审核通过，人数未满 5、审核通过，人数已满
     *
     * @param jobId
     * @return
     */
    @PutMapping("updateJobStatusByJobId/{jobId}")
    public ResponseEntity<ResponseResult<Boolean>> updateJobStatusByJobId(@PathVariable("jobId") Long jobId,
                                                                          @RequestParam("status") int status) {

        // 是否修改成功
        boolean flag = jobService.updateJobStatusByJobId(jobId, status);

        // 如果修改失败，抛出异常并返回结果
        if (!flag) {
            throw new MyException(ExceptionEnum.UPDATE_JOB_STATUS_FAIL);
        }


        return ResponseEntity.ok(new ResponseResult<>(200, "修改工作状态成功", flag));
    }


    /**
     * 描述：根据jobId修改job信息
     *
     * @param jobId
     * @return
     */
    @PutMapping("updateJobByJobId/{jobId}")
    public ResponseEntity<ResponseResult<Boolean>> updateJobByJobId(@PathVariable("jobId") Long jobId,
                                                                    JobgoJgDbJob job) {

        // 是否修改成功
        boolean flag = jobService.updateJobByJobId(jobId, job);

        // 如果修改失败，抛出异常并返回结果
        if (!flag) {
            throw new MyException(ExceptionEnum.UPDATE_JOB_STATUS_FAIL);
        }

        return ResponseEntity.ok(new ResponseResult<>(200, "修改工作成功", flag));
    }

}
