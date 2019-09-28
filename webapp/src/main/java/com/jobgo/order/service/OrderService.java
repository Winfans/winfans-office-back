package com.jobgo.order.service;

import com.jobgo.order.dto.OrderDTO;
import com.jobgo.order.utils.PayState;

import java.util.Map;

/**
 * 描述：订单Service
 * 创建时间: 2019-08-13 8:41
 * 修改时间: 2019-08-13 8:41
 */
public interface OrderService {

    /**
     * 描述：创建订单
     *
     * @param orderDTO
     * @return
     */
    Long createOrder(OrderDTO orderDTO);

    /**
     * 描述：根据订单号查询订单
     *
     * @param orderId
     * @return
     */
    OrderDTO findOrderByOrderId(Long orderId);

    /**
     * 描述：根据订单id生成支付链接
     *
     * @param orderId
     * @return
     */
    String createPayUrl(long orderId);


    /**
     * 更新订单状态
     * @param id
     * @param status
     * @return
     */
    Boolean updateOrderStatus(Long id, Integer status);

    /**
     * 描述：处理支付成功回调
     * @param result
     */
    void handleNotify(Map<String, String> result);

    /**
     * 描述：查询订单状态
     * @param orderId
     * @return
     */
    PayState findOrderStatus(long orderId);
}
