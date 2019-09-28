package com.jobgo.order.controller;


import com.jobgo.commom.enums.ExceptionEnum;
import com.jobgo.commom.exception.MyException;
import com.jobgo.commom.vo.ResponseResult;
import com.jobgo.order.dto.OrderDTO;
import com.jobgo.order.service.OrderService;
import com.jobgo.order.utils.PayState;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * 描述：订单Controller
 * 创建时间: 2019-08-13 8:41
 * 修改时间: 2019-08-13 8:41
 */
@RestController
@Api("订单服务接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 描述：创建订单
     *
     * @param orderDTO 订单对象
     * @return 订单编号
     */
    @PostMapping("createOrder")
    @ApiOperation(value = "创建订单接口，返回订单编号", notes = "创建订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderDTO", required = true, value = "订单的json对象，包含企业id和订单详情"),
    })
    public ResponseEntity<ResponseResult<List<Long>>> createOrder(@RequestBody @Valid OrderDTO orderDTO) {
        Long orderId = this.orderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseResult<>(201, "订单创建成功", Arrays.asList(orderId)));
    }

    /**
     * 描述：查询订单
     *
     * @param orderId 订单对象
     * @return 订单编号
     */
    @GetMapping("{orderId}")
    public ResponseEntity<ResponseResult<OrderDTO>> findOrderByOrderId(@PathVariable("orderId") long orderId) {
        OrderDTO order = orderService.findOrderByOrderId(orderId);
        if (order == null) {
            throw new MyException(ExceptionEnum.ORDER_NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "订单查询成功", order));
    }

    /**
     * 描述：根据订单id生成支付链接
     *
     * @param orderId
     * @return
     */
    @GetMapping("/payUrl/{orderId}")
    public ResponseEntity<ResponseResult<String>> createPayUrl(@PathVariable("orderId") long orderId) {

        String payUrl = orderService.createPayUrl(orderId);


        if (StringUtils.isBlank(payUrl)) {
            throw new MyException(ExceptionEnum.WX_PAY_ERROR);
        }

        return ResponseEntity.ok(new ResponseResult<>(200, "订单查询成功", payUrl));
    }

    /**
     * 描述：查询订单状态
     * @param orderId
     * @return
     */
    @GetMapping("state/{orderId}")
    public ResponseEntity<ResponseResult<PayState>> findOrderStatus(@PathVariable("orderId") long orderId) {

        return ResponseEntity.ok(new ResponseResult<>(200, "订单状态查询成功", orderService.findOrderStatus(orderId)));
    }

}
