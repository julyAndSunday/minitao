package com.minitao.user.dto;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-11-15 19:45
 **/
public class CodeLoginDto {
    private String phone;
    private String code;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
