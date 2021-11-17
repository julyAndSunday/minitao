package com.minitao.order.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.minitao.common.entity.User;
import lombok.Data;
import org.yaml.snakeyaml.events.Event;

/**
 * tao_receive
 * @author 
 */
@Data
@TableName("tao_receive")
public class Receive  {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 收货人手机
     */
    private String phone;

    /**
     * 收货地址
     */
    private String address;

}