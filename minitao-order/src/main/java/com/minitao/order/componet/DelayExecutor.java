package com.minitao.order.componet;

import com.minitao.order.OrderStatus;
import com.minitao.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-11-15 22:48
 **/
@Component
public class DelayExecutor {
    private final int delayTime = 15;
    private ScheduledExecutorService executors = Executors.newScheduledThreadPool(3);
    @Autowired
    private OrderService orderService;

    public void add(int orderId) {
        executors.schedule(new OrderCancel(orderId), delayTime, TimeUnit.SECONDS);
    }

    class OrderCancel implements Runnable {
        int orderId;

        public OrderCancel(int orderId) {
            this.orderId = orderId;
        }

        @Override
        public void run() {
            orderService.updateStatus(orderId, OrderStatus.CANCEL.getStatus(), OrderStatus.NoPAY.getStatus());
        }
    }
}
