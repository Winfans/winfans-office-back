package top.wffanshao.office.service;

import top.wffanshao.office.dto.CustomerDTO;
import top.wffanshao.office.pojo.OfficeDbCustomer;
import top.wffanshao.office.vo.ResponsePage;

import java.util.List;

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
}
