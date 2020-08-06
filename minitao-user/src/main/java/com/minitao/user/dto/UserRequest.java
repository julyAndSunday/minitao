package com.minitao.user.dto;

/**
 * @Description:  用户注册登录参数类
 * @Author: July
 * @Date: 2020-07-23 21:17
 **/

public class UserRequest {
    private String username;
    private String password;
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
