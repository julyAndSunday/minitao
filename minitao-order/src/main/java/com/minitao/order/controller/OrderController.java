package com.minitao.order.controller;

import com.minitao.common.annotation.CurrentUser;
import com.minitao.common.entity.User;
import com.minitao.common.response.CommonResult;
import com.minitao.order.dto.OrderDto;
import com.minitao.order.entity.Order;
import com.minitao.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/add")
    public CommonResult postOrder(@RequestBody OrderDto orderDto){
        orderService.addOrder(orderDto);
        return CommonResult.success(orderDto.getOrder());
    }

    @GetMapping("/list")
    public CommonResult getMyOrders(@CurrentUser User user){
        List<Order> orderDto = orderService.getMyOrders(user);
        return CommonResult.success(orderDto);
    }

}
