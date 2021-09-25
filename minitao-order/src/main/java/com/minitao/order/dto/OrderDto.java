package com.minitao.order.dto;

import com.minitao.order.entity.Order;
import com.minitao.order.entity.OrderDetail;
import lombok.Data;
import org.aspectj.weaver.ast.Or;

import java.util.List;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-09-24 19:03
 **/
@Data
public class OrderDto {
    private Order order;
    private OrderDetailDto orderDetailDto;
}
