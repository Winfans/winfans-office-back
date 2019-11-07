package top.wffanshao.office.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.wffanshao.office.pojo.OfficeDbUserCustomer;

/**
 * 描述：用户-客户DAO
 *
 * @author 杨炜帆
 * @date 2019/10/17
 */
public interface UserCustomerDAO extends JpaRepository<OfficeDbUserCustomer, Integer>, JpaSpecificationExecutor<OfficeDbUserCustomer> {


    /**
     * 描述：根据客户id查找用户-客户
     *
     * @param customerId
     * @return
     */
    OfficeDbUserCustomer findByCustomerId(int customerId);

    /**
     * 根据用户id和客户id删除客户-用户
     *
     * @param customerId
     * @param userId
     */
    void deleteByCustomerIdAndUserId(Integer customerId, Integer userId);

    void deleteByUserId(Integer userId);

    /**
     * 根据客户id判断是否存在
     *
     * @param customerId
     * @return
     */
    boolean existsByCustomerIdAndUserId(Integer customerId, Integer userId);

    boolean existsByUserId(Integer userId);
}
