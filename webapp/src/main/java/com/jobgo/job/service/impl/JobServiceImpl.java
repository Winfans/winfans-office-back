package com.jobgo.job.service.impl;

import com.jobgo.job.pojo.JobgoJgDbJob;
import com.jobgo.job.service.JobService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：工作Service实现类
 * 创建时间: 2019-09-15 10:55
 * 修改时间: 2019-09-17 20:26
 */
@Service
public class JobServiceImpl implements JobService {
    /**
     * 描述：发布工作提交审核
     * 状态：1、待审核 2、审核通过 3、审核未通过 4、审核通过，人数未满 5、审核通过，人数已满
     *
     * @param job
     * @return
     */
    @Override
    public Boolean createJob(JobgoJgDbJob job) {
        return null;
    }

    /**
     * 描述：查询所有企业发布的工作
     *
     * @return
     */
    @Override
    public List<JobgoJgDbJob> findAllJob() {
        return null;
    }

    /**
     * 描述：根据企业id查询该企业发布的所有工作
     *
     * @param endId
     * @return
     */
    @Override
    public List<JobgoJgDbJob> findAllJobByEntId(String endId) {
        return null;
    }

    /**
     * 描述：根据jobId查询job
     *
     * @param jobId
     * @return
     */
    @Override
    public JobgoJgDbJob findJobByJobId(Long jobId) {
        return null;
    }

    /**
     * 描述：分页查询所有企业发布工作
     *
     * @param page 页数
     * @param size 显示数量
     * @return
     */
    @Override
    public Page<JobgoJgDbJob> findJobByPage(int page, int size) {
        return null;
    }

    /**
     * 描述：根据企业id分页查询某个企业发布的工作
     *
     * @param entId
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<JobgoJgDbJob> findJobByEntIdAndPage(String entId, int page, int size) {
        return null;
    }

    /**
     * 描述：根据jobId删除job
     *
     * @param jobId
     * @return
     */
    @Override
    public boolean deleteJobByJobId(Long jobId) {
        return false;
    }

    /**
     * 描述：根据企业id删除该企业发布的所有工作
     *
     * @param entId
     * @return
     */
    @Override
    public boolean deleteAllJobByEntId(String entId) {
        return false;
    }

    /**
     * 描述：根据jobId修改job状态
     * 状态：1、待审核 2、审核通过 3、审核未通过 4、审核通过，人数未满 5、审核通过，人数已满
     *
     * @param jobId
     * @param status
     * @return
     */
    @Override
    public boolean updateJobStatusByJobId(Long jobId, int status) {
        return false;
    }

    /**
     * 描述：根据jobId修改job信息
     *
     * @param jobId
     * @param job
     * @return
     */
    @Override
    public boolean updateJobByJobId(Long jobId, JobgoJgDbJob job) {
        return false;
    }
}
