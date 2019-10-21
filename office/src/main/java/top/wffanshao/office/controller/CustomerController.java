package top.wffanshao.office.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.wffanshao.office.dto.CustomerDTO;
import top.wffanshao.office.enums.ExceptionEnum;
import top.wffanshao.office.exception.MyException;
import top.wffanshao.office.pojo.OfficeDbCustomer;
import top.wffanshao.office.pojo.OfficeDbUser;
import top.wffanshao.office.service.CustomerService;
import top.wffanshao.office.service.UserService;
import top.wffanshao.office.vo.ResponsePage;
import top.wffanshao.office.vo.ResponseResult;

import java.util.List;


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


}
