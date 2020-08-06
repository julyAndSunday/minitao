package com.minitao.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author july
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tao_user")
@ApiModel(value = "TaoUser对象")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String icon;

    private Date createTime;

    private Date loginTime;


}
