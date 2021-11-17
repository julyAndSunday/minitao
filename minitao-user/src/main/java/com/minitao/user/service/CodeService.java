package com.minitao.user.service;

public interface CodeService {
    void sendCode(String phone);

    boolean verifyCode(String phone, String code);
}


