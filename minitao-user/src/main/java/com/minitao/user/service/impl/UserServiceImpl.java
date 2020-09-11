package com.minitao.user.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.minitao.common.response.CommonResult;
import com.minitao.user.dto.UserRequest;
import com.minitao.user.entity.User;
import com.minitao.user.mapper.UserMapper;
import com.minitao.user.rpc.AuthService;
import com.minitao.user.service.UserService;
import com.minitao.user.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author july
 * @since 2020-07-23
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthService authService;

    private static final String userPre = "taoUser:";

    @Override
    public CommonResult login(UserRequest userRequest) {
        Map param = new HashMap<>();
        param.put("client_id", "client");
        param.put("client_secret", "secret");
        param.put("grant_type", "password");
        param.put("username", userRequest.getUsername());
        param.put("password", userRequest.getPassword());
        CommonResult commonResult = authService.getAccessToken(param);
        if (200 == commonResult.getCode() && commonResult.getData() != null) {
//            log
        }
        return commonResult;

    }
    @Override
    public String refreshToken(String token) {
        if (jwtTokenUtil.canRefresh(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    public static void main(String[] args) {
        String hashpw = BCrypt.hashpw(String.valueOf(123456));
        System.out.println(hashpw);
    }
    @Override
    public User register(UserRequest userRequest) {
        //查询用户名是否已存在
        User user = userMapper.selectUserByName(userRequest.getUsername());
        if (user != null){
            return null;
        }
        User newUser = new User();
        String encodePassword = BCrypt.hashpw(userRequest.getPassword());

        newUser.setId(null);
        newUser.setUsername(userRequest.getUsername());
        newUser.setPassword(encodePassword);
        newUser.setNickname(userRequest.getNickname());
        newUser.setCreateTime(new Date());
        userMapper.insert(newUser);
        return newUser;
    }

    @Override
    public User getCurrentUser(String token) {
        String userName = jwtTokenUtil.getUserNameFromToken(token);
        System.out.println(token);
        System.out.println(userName);
        return userMapper.selectUserByName(userName);
    }


    @Override
    public User loadUsername(String username) {
       return userMapper.selectUserByName(username);
    }

}
