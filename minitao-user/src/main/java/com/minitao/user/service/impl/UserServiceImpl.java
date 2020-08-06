package com.minitao.user.service.impl;

import com.minitao.user.dto.UserRequest;
import com.minitao.user.entity.User;
import com.minitao.user.mapper.UserMapper;
import com.minitao.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minitao.user.utils.EncryptUtil;
import com.minitao.user.utils.JwtTokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    private EncryptUtil encryptUtil;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String USERKEY = "taoUser:";

    @Override
    public String login(UserRequest userRequest) {
        User user = userMapper.validate(userRequest.getUsername(), encryptUtil.encrypt(userRequest.getPassword()));
        if (user != null){
            redisTemplate.opsForValue().set(USERKEY+user.getId(),user.getUsername());
            return jwtTokenUtil.generateToken(user);
        }
        return null;
    }

    @Override
    public String refreshToken(String token) {
        if (jwtTokenUtil.canRefresh(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    @Override
    public User register(UserRequest userRequest) {
        //查询用户名是否已存在
        User user = userMapper.selectUserByName(userRequest.getUsername());
        if (user != null){
            return null;
        }
        User newUser = new User();
        String encodePassword = encryptUtil.encrypt(userRequest.getPassword());
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
       return userMapper.selectUserByName(userName);
    }

}
