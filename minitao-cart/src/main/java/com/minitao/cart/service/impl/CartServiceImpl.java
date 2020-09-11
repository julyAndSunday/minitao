package com.minitao.cart.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.minitao.cart.dto.UserDto;
import com.minitao.cart.entity.Cart;
import com.minitao.cart.rpc.UserFeign;
import com.minitao.cart.mapper.CartMapper;
import com.minitao.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author july
 * @since 2020-07-21
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private HttpServletRequest request;

    private static final String userPre = "taoUser:";

    @Override
    public void add(Cart cart) {
        cart.setId(null);
        String userInfo = request.getHeader("UserInfo");
        UserDto userDto = JSONUtil.toBean(userInfo, UserDto.class);
        cart.setUserId(userDto.getId());
//        cart.setPrice();  //价格不能用前端传来的
        System.out.println(cart);
        cartMapper.insert(cart);
    }



}
