package com.jobgo.order.service;

import com.jobgo.order.pojo.JobgoJgDbContract;

/**
 * 描述：合同Service
 * 创建时间: 2019-09-10 8:38
 * 修改时间: 2019-09-10 8:38
 */
public interface ContractService {

    /**
     * 描述：创建合同
     *
     * @param contract
     */
    void createContract(JobgoJgDbContract contract);
}
