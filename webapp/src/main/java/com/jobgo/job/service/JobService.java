package com.jobgo.job.service;

import com.jobgo.job.pojo.JobgoJgDbJob;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 描述：工作Service
 * 创建时间: 2019-09-15 10:55
 * 修改时间: 2019-09-17 20:26
 */
public interface JobService {
    /**
     * 描述：发布工作提交审核
     * 状态：1、待审核 2、审核通过 3、审核未通过 4、审核通过，人数未满 5、审核通过，人数已满
     *
     * @param job
     * @return
     */
    Boolean createJob(JobgoJgDbJob job);
    /**
     * 描述：查询所有企业发布的工作
     *
     * @return
     */
    List<JobgoJgDbJob> findAllJob();
    /**
     * 描述：根据企业id查询该企业发布的所有工作
     *
     * @param endId
     * @return
     */
    List<JobgoJgDbJob> findAllJobByEntId(String endId);
    /**
     * 描述：根据jobId查询job
     *
     * @param jobId
     * @return
     */
    JobgoJgDbJob findJobByJobId(Long jobId);

    /**
     * 描述：分页查询所有企业发布工作
     *
     * @param page 页数
     * @param size 显示数量
     * @return
     */
    Page<JobgoJgDbJob> findJobByPage(int page, int size);

    /**
     * 描述：根据企业id分页查询某个企业发布的工作
     *
     * @param entId
     * @param page
     * @param size
     * @return
     */
    Page<JobgoJgDbJob> findJobByEntIdAndPage(String entId, int page, int size);

    /**
     * 描述：根据jobId删除job
     *
     * @param jobId
     * @return
     */
    boolean deleteJobByJobId(Long jobId);

    /**
     * 描述：根据企业id删除该企业发布的所有工作
     *
     * @param entId
     * @return
     */
    boolean deleteAllJobByEntId(String entId);

    /**
     * 描述：根据jobId修改job状态
     * 状态：1、待审核 2、审核通过 3、审核未通过 4、审核通过，人数未满 5、审核通过，人数已满
     *
     * @param jobId
     * @return
     */
    boolean updateJobStatusByJobId(Long jobId, int status);

    /**
     * 描述：根据jobId修改job信息
     *
     * @param jobId
     * @return
     */
    boolean updateJobByJobId(Long jobId, JobgoJgDbJob job);
}
