package com.minitao.order.service;

import com.item.common.dto.StockDto;
import com.minitao.order.dto.OrderDetailDto;
import com.minitao.order.dto.OrderDto;
import com.minitao.order.entity.OrderDetail;
import com.minitao.order.mapper.OrderDetailMapper;
import com.minitao.order.mapper.OrderMapper;
import com.minitao.order.rpc.StockService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description:
 * @Author: July
 * @Date: 2021-09-24 17:07
 **/
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private StockService stockService;

    @GlobalTransactional
    public void postService(OrderDto orderDto) {
        orderMapper.insert(orderDto.getOrder());
        OrderDetail orderDetail = new OrderDetail();
        OrderDetailDto orderDetailDto = orderDto.getOrderDetailDto();
        String count = StringUtils.join(orderDetailDto.getCount(), ",");
        String skuId = StringUtils.join(orderDetailDto.getSkuId(), ",");
        String price = StringUtils.join(orderDetailDto.getPrice(), ",");

        orderDetail.setOrderId(orderDto.getOrder().getId());
        orderDetail.setCount(count);
        orderDetail.setSkuId(skuId);
        orderDetail.setPrice(price);
        orderDetailMapper.insert(orderDetail);
        stockService.subtractStock(new StockDto(orderDetailDto.getSkuId(),orderDetailDto.getCount()));
    }
}
