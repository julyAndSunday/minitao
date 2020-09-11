package com.minitao.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService  {
    public UserDetails loadUserByUsername(String username);
}
