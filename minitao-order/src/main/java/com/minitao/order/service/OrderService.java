package com.minitao.order.service;

import com.minitao.common.entity.User;
import com.minitao.order.dto.OrderDto;
import com.minitao.order.entity.Order;

import java.util.List;

public interface OrderService {
    void addOrder(OrderDto orderDto);

    List<Order> getMyOrders(User user);

    void pay(int orderId);

    void receive(int orderId);


    void updateStatus(int orderId, int newStatus, int oldStatus);
}

