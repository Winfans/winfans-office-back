package com.jobgo.order.dao;

import com.jobgo.order.pojo.JobgoJgDbContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 描述：合同DAO
 * 创建时间: 2019-09-10 8:38
 * 修改时间: 2019-09-10 8:38
 */
public interface ContractDAO extends JpaRepository<JobgoJgDbContract, Long>, JpaSpecificationExecutor<JobgoJgDbContract> {
}
