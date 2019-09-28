package com.jobgo.order.service.impl;

import com.jobgo.order.dao.ContractDAO;
import com.jobgo.order.dao.ContractStatusDAO;
import com.jobgo.order.pojo.JobgoJgDbContract;
import com.jobgo.order.pojo.JobgoJgDbContractStatus;
import com.jobgo.order.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 描述：合同Service实现类
 * 创建时间: 2019-09-10 8:38
 * 修改时间: 2019-09-10 8:38
 */
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractDAO contractDAO;

    @Autowired
    private ContractStatusDAO contractStatusDAO;

    /**
     * 描述：创建合同
     *
     * @param contract
     */
    @Override
    @Transactional
    public void createContract(JobgoJgDbContract contract) {

        JobgoJgDbContract savedContract = contractDAO.saveAndFlush(contract);

        if (savedContract.getConId() == 0) {
            return;
        }

        JobgoJgDbContractStatus contractStatus = new JobgoJgDbContractStatus();

        contractStatus.setConId(savedContract.getConId());

        // 初始化合同状态为生效状态
        contractStatus.setStatus(1);

        contractStatus.setStartTime(new Timestamp(new Date().getTime()));

        contractStatusDAO.saveAndFlush(contractStatus);


    }
}
