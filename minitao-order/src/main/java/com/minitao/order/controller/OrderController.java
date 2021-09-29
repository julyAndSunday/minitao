package com.minitao.order.controller;

import com.minitao.common.response.CommonResult;
import com.minitao.order.dto.OrderDto;
import com.minitao.order.entity.Order;
import com.minitao.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-09-24 16:52
 **/
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 下单操作
     * @param orderDto
     */
    @PostMapping("/post")
    public CommonResult<Order> postOrder(@RequestBody OrderDto orderDto){
        orderService.postService(orderDto);
        return CommonResult.success(orderDto.getOrder());
    }

}
