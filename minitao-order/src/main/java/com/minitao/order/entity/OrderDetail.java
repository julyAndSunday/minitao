package com.minitao.order.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * tao_order_detail
 *
 * @author
 */
@Data
@TableName("tao_order_detail")
public class OrderDetail {
    private int orderId;

    /**
     * 商品id
     */
    private String skuId;

    /**
     * 数量
     */
    private String count;

    /**
     * 总金额
     */
    private String price;

    /**
     * 图片
     */
    private String image;

}