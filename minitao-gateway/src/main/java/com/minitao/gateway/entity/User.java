package com.minitao.gateway.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author july
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tao_user")
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
