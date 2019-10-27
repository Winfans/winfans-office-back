package top.wffanshao.office.service;

import top.wffanshao.office.dto.CustomerDTO;
import top.wffanshao.office.pojo.OfficeDbCustomer;
import top.wffanshao.office.vo.ResponsePage;


/**
 * 描述：客户Service
 *
 * @author 杨炜帆
 * @date 2019/10/17
 */
public interface CustomerService {

    /**
     * 描述：添加客户
     *
     * @param customer 客户
     * @param token    cookies
     * @return 是否添加成功
     */
    Boolean addCustomer(OfficeDbCustomer customer, String token);

    /**
     * 描述：根据团队id分页查询所有客户
     *
     * @param teamId
     * @param page
     * @param size
     * @return
     */
    ResponsePage<CustomerDTO> findAllCustomerTeamIdAndByPage(int teamId, int page, int size);

    /**
     * 描述：删除客户
     *
     * @param token
     * @param customerId
     * @return
     */
    boolean deleteCustomerByCustomerId(String token, Integer customerId);

    /**
     * 描述：根据id修改相对应的客户
     *
     * @param token
     * @param customerId
     * @param customerDTO
     * @return
     */
    Boolean updateCustomerByCustomerId(String token, Integer customerId, CustomerDTO customerDTO);

    /**
     * 描述：根据customerId查询客户
     *
     * @param customerId
     * @return
     */
    CustomerDTO findCustomerByCustomerId(int customerId);
}
