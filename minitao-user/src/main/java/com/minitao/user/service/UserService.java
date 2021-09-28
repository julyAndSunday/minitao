package com.minitao.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minitao.common.response.CommonResult;
import com.minitao.user.dto.UserRequest;
import com.minitao.user.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author july
 * @since 2020-07-23
 */
public interface UserService  {

    String login(UserRequest userRequest);

    String refreshToken(String token);

    User register(UserRequest userRequest);

    User getCurrentUser(String token);


}
