package com.minitao.user.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.minitao.common.utils.JwtTokenUtil;
import com.minitao.user.dto.CodeLoginDto;
import com.minitao.user.dto.UserRequest;
import com.minitao.user.entity.User;
import com.minitao.user.mapper.UserMapper;
import com.minitao.user.service.CodeService;
import com.minitao.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author july
 * @since 2020-07-23
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Resource
    private UserMapper userMapper;
    @Autowired
    private CodeService codeService;
    private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    private static final String userPre = "taoUser:";



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
        if (user != null) {
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
        return null;
    }

    @Override
    public User getCurrentUser(String token) {
        String userName = jwtTokenUtil.getUserNameFromToken(token);
        return userMapper.selectUserByName(userName);
    }

    @Override
    public String login(UserRequest userRequest) {
        User userDetails = loadUserByUsername(userRequest.getUsername());
        if(BCrypt.checkpw(userRequest.getPassword(),userDetails.getPassword())){
            com.minitao.common.entity.User user = new com.minitao.common.entity.User();
            BeanUtils.copyProperties(userDetails,user);
            return jwtTokenUtil.generateToken(user);
        }else {
            return null;
        }
    }

    public String login(CodeLoginDto codeLoginDto){
        boolean verify = codeService.verifyCode(codeLoginDto.getPhone(), codeLoginDto.getCode());
        if (verify){
            User userDetails = userMapper.selectUserByPhone(codeLoginDto.getPhone());
            com.minitao.common.entity.User user = new com.minitao.common.entity.User();
            BeanUtils.copyProperties(userDetails,user);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            //添加用户权限  可有可无？
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtTokenUtil.generateToken(user);

        }
        return null;
    }


    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.selectUserByName(username);
    }

}
