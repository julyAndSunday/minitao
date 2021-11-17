package com.minitao.cart.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author july
 * @since 2020-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tao_cart")
public class Cart  {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long skuId;

    private Long count;

    @ApiModelProperty(value = "总金额")
    private Double price;


}
