package com.minitao.order.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * tao_order
 * @author 
 */
@Data
@TableName("tao_order")
public class Order {
    /**
     * 订单id
     */
    private int id;

    /**
     * 用户id
     */
    private int userId;

    /**
     * 收货信息id
     */
    private int receiverId;

    /**
     * 付款金额
     */
    private Double pay;

    private Integer receiverStatus;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单状态
     */
    private int status;

}