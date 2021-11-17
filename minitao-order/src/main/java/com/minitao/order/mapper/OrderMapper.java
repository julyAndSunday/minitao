package com.minitao.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minitao.order.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-09-24 17:07
 **/
public interface OrderMapper extends BaseMapper<Order> {
    List<Order> selectByUserId(@Param("id") Long id);

    void updateStatusByOrderId(@Param("orderId") int orderId, @Param("newStatus") int newStatus, @Param("oldStatus") int oldStatus);
}
