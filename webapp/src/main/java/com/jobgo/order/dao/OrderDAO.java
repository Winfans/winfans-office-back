package com.jobgo.order.dao;

import com.jobgo.order.pojo.JobgoJgDbOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 描述：订单DAO
 * 创建时间: 2019-08-13 8:41
 * 修改时间: 2019-08-13 8:41
 */
public interface OrderDAO extends JpaRepository<JobgoJgDbOrder, Long>, JpaSpecificationExecutor<JobgoJgDbOrder> {
}
