package top.wffanshao.office.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.wffanshao.office.pojo.OfficeDbCustomer;

/**
 * 描述：客户DAO
 *
 * @author 杨炜帆
 * @date 2019/10/17
 */
public interface CustomerDAO  extends JpaRepository<OfficeDbCustomer, Integer>, JpaSpecificationExecutor<OfficeDbCustomer> {
}
