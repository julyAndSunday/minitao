package com.minitao.cart.service.impl;

import com.minitao.cart.entity.Cart;
import com.minitao.cart.mapper.CartMapper;
import com.minitao.cart.service.CartService;
import com.minitao.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author july
 * @since 2020-07-21
 */
@Service
public class CartServiceImpl implements CartService {

    @Resource
    private CartMapper cartMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private HttpServletRequest request;

    private static final String userPre = "taoUser:";

    @Override
    public void add(Cart cart,User user) {
        cart.setId(null);
        cart.setUserId(user.getId());
//        cart.setPrice();  //价格不能用前端传来的
        System.out.println(cart);
        cartMapper.insert(cart);
    }

    @Override
    public List<Cart> getCarts(User user) {
        return cartMapper.selectCartByUserId(user.getId());
    }


}
