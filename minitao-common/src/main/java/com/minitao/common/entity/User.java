package com.minitao.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author july
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User {

    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String phone;

    private String icon;

    private Date createTime;

    private Date loginTime;


}
