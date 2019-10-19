package top.wffanshao.office.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wffanshao.office.bo.UserInfo;
import top.wffanshao.office.dao.CustomerDAO;
import top.wffanshao.office.dao.UserCustomerDAO;
import top.wffanshao.office.dao.UserDAO;
import top.wffanshao.office.dao.UserTeamDAO;
import top.wffanshao.office.dto.CustomerDTO;
import top.wffanshao.office.enums.ExceptionEnum;
import top.wffanshao.office.exception.MyException;
import top.wffanshao.office.pojo.OfficeDbCustomer;
import top.wffanshao.office.pojo.OfficeDbUser;
import top.wffanshao.office.pojo.OfficeDbUserCustomer;
import top.wffanshao.office.properties.JwtProperties;
import top.wffanshao.office.service.CustomerService;
import top.wffanshao.office.utils.JwtUtils;
import top.wffanshao.office.vo.ResponsePage;

import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * 描述：客户Service
 *
 * @author 杨炜帆
 * @date 2019/10/17
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private UserCustomerDAO userCustomerDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 描述：添加客户
     *
     * @param customer 客户
     * @return 是否添加成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addCustomer(OfficeDbCustomer customer, String token) {

        // 解析token获取用户id
        UserInfo userInfo;

        try {
            userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            log.error("[团队服务] 解析用户token失败{}", e);
            return false;
        }

        customer.setCreateTime(new Timestamp(System.currentTimeMillis()));

        Optional<OfficeDbUser> optional = userDAO.findById(userInfo.getUserId());
        if (optional.isPresent()) {
            OfficeDbUser user = optional.get();
            customer.setCreator(user.getUserName());
        }

        OfficeDbCustomer savedCustomer = customerDAO.saveAndFlush(customer);

        if (savedCustomer.getCustomerId() == 0) {
            throw new MyException(ExceptionEnum.CUSTOMER_ADD_FAIL);
        }

        OfficeDbUserCustomer userCustomer = new OfficeDbUserCustomer();

        userCustomer.setUserId(userInfo.getUserId());

        userCustomer.setCustomerId(savedCustomer.getCustomerId());
        OfficeDbUserCustomer savedUserCustomer = userCustomerDAO.saveAndFlush(userCustomer);

        if (savedUserCustomer == null) {
            throw new MyException(ExceptionEnum.CUSTOMER_ADD_FAIL);
        }

        return true;
    }

    /**
     * 描述：根据团队id分页查询所有客户
     *
     * @param teamId
     * @param page
     * @param size
     * @return
     */
    @Override
    public ResponsePage<CustomerDTO> findAllCustomerTeamIdAndByPage(int teamId, int page, int size) {

        page = page <= 0 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(page, size);

        Specification<OfficeDbCustomer> specification = (Specification<OfficeDbCustomer>) (root, query, criteriaBuilder) -> {
            Path<Object> path = root.get("teamId");
            return criteriaBuilder.equal(path, teamId);
        };

        Page<OfficeDbCustomer> customerPage = customerDAO.findAll(specification, pageable);

        if (customerPage == null) {
            throw new MyException(ExceptionEnum.CUSTOMER_NOT_FOUND);
        }

        List<OfficeDbCustomer> customerList = customerPage.getContent();
        List<CustomerDTO> customerDtoList = new LinkedList<>();
        customerList.forEach(customer -> {

            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setCustomerName(customer.getCustomerName());
            customerDTO.setStatus(customer.getStatus());
            customerDTO.setCreateTime(customer.getCreateTime());
            customerDTO.setCreator(customer.getCreator());

            OfficeDbUserCustomer userCustomer = userCustomerDAO.findByCustomerId(customer.getCustomerId());
            Optional<OfficeDbUser> optional = userDAO.findById(userCustomer.getUserId());
            optional.ifPresent(officeDbUser -> customerDTO.setUserName(officeDbUser.getUserName()));
            customerDtoList.add(customerDTO);
        });

        ResponsePage<CustomerDTO> customerResponsePage = new ResponsePage<>();
        customerResponsePage.setContent(customerDtoList);
        customerResponsePage.setTotalPages(customerPage.getTotalPages());
        customerResponsePage.setTotalElements((int) customerPage.getTotalElements());

        return customerResponsePage;
    }
}
