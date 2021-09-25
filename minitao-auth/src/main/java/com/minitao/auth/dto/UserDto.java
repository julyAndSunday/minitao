package com.minitao.auth.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author july
 * @since 2020-07-23
 */
@Data
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String icon;

    private Date createTime;

    private Date loginTime;


}
