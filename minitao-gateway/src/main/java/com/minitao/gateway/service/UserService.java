package com.minitao.gateway.service;

import com.minitao.gateway.entity.User;
import com.minitao.gateway.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: July
 * @Date: 2020-07-28 23:26
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User loadUserByUsername(String name){
        return userMapper.selectUserByName(name);
    }
}
