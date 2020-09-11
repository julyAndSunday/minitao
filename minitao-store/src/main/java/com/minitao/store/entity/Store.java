package com.minitao.store.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author July
 * @since 2020-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tao_store")
public class Store {


    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    /**
     * 店铺名称
     */
    private String name;

    /**
     * 简介
     */
    private String brief;

    /**
     * 店铺图标
     */
    private String icon;

    /**
     * 点赞数
     */
    private Integer likes;

}
