package top.wffanshao.office.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.wffanshao.office.dto.CustomerDTO;
import top.wffanshao.office.enums.ExceptionEnum;
import top.wffanshao.office.exception.MyException;
import top.wffanshao.office.pojo.OfficeDbCustomer;
import top.wffanshao.office.service.CustomerService;
import top.wffanshao.office.vo.ResponsePage;
import top.wffanshao.office.vo.ResponseResult;


/**
 * 描述：客户Controller
 *
 * @author 杨炜帆
 * @date 2019/10/17
 */
@RestController
@RequestMapping("customer")
public final class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 描述：添加客户
     *
     * @param customer
     * @return
     */
    @PostMapping("addCustomer")
    public ResponseEntity<ResponseResult<Void>> addCustomer(OfficeDbCustomer customer, @CookieValue("OFFICE_TOKEN") String token) {
        Boolean result = this.customerService.addCustomer(customer, token);
        if (!result) {
            throw new MyException(ExceptionEnum.CUSTOMER_ADD_FAIL);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseResult<>(201, "客户添加成功"));
    }

    /**
     * 描述：分页查询所有客户
     *
     * @param teamId
     * @param page   当前页数
     * @param size   总叶数
     * @return
     */
    @GetMapping("findAllCustomerTeamIdAndByPage/{teamId}/{page}/{size}")
    public ResponseEntity<ResponseResult<ResponsePage<CustomerDTO>>> findAllCustomerTeamIdAndByPage(@PathVariable("teamId") int teamId,
                                                                                                    @PathVariable("page") int page,
                                                                                                    @PathVariable("size") int size) {
        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", customerService.findAllCustomerTeamIdAndByPage(teamId, page, size)));
    }

    /**
     * 描述：删除客户
     *
     * @param token
     * @param customerIdList
     * @return
     */
    @DeleteMapping("deleteCustomerByCustomerId")
    public ResponseEntity<ResponseResult<Void>> deleteCustomerByCustomerId(
            @CookieValue("OFFICE_TOKEN") String token,
            @RequestParam("customerIdList") String customerIdList
    ) {

        String[] customerIds = customerIdList.split(",");


        boolean result = false;
        for (String customerId : customerIds) {

            System.out.println(customerId);
            result = customerService.deleteCustomerByCustomerId(token, Integer.parseInt(customerId));
        }
        if (!result) {
            throw new MyException(ExceptionEnum.CUSTOMER_DELETE_FAIL);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "删除成功"));
    }

    /**
     * 描述：根据customerId查询客户
     *
     * @param customerId
     * @return
     */
    @GetMapping("findCustomerByCustomerId/{customerId}")
    public ResponseEntity<ResponseResult<CustomerDTO>> findCustomerByCustomerId(@PathVariable("customerId") int customerId) {
        CustomerDTO customerDTO = customerService.findCustomerByCustomerId(customerId);
        if (customerDTO == null) {
            throw new MyException(ExceptionEnum.CUSTOMER_NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", customerDTO));
    }


    /**
     * 描述：根据id修改相对应的客户
     *
     * @param customerDTO
     * @param token
     * @param customerId
     * @return
     */
    @PostMapping("updateCustomerByCustomerId/{customerId}")
    public ResponseEntity<ResponseResult<Void>> updateCustomerByCustomerId(
            CustomerDTO customerDTO,
            @CookieValue("OFFICE_TOKEN") String token,
            @PathVariable("customerId") Integer customerId
    ) {
        System.out.println(customerDTO.getCustomerName());
        Boolean result = customerService.updateCustomerByCustomerId(token, customerId, customerDTO);
        if (!result) {
            throw new MyException(ExceptionEnum.CUSTOMER_UPDATE_FAIL);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "修改成功"));
    }


}
