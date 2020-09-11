package com.minitao.auth.service.impl;

import com.minitao.auth.domain.SecurityUser;
import com.minitao.auth.dto.UserDto;
import com.minitao.auth.rpc.UserFeign;
import com.minitao.auth.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: July
 * @Date: 2020-08-22 09:59
 **/
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserFeign userFeign;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDto userDto = userFeign.loadUserByUsername(username);
        SecurityUser securityUser = new SecurityUser();
        BeanUtils.copyProperties(userDto,securityUser);
        return securityUser;
    }
}
