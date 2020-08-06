package com.minitao.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.minitao.cart.entity.Cart;
import com.minitao.cart.entity.User;
import com.minitao.cart.mapper.CartMapper;
import com.minitao.cart.service.CartService;
import com.minitao.common.response.CommonResult;
import com.minitao.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author july
 * @since 2020-07-21
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private UserFeign userFeign;

    @Override
    public void add(Cart cart) {
        cart.setId(null);
        User user = userFeign.getCurrentUser();
        cart.setUserId(user.getId());
        cartMapper.insert(cart);
    }

    @Override
    public List<Cart> getCarts() {
        User user = userFeign.getCurrentUser();
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Cart::getUserId,user.getId());
        List<Cart> carts = cartMapper.selectList(wrapper);
        return carts;
    }

    public Cart getCartDetail(Long skuId){
        User user = userFeign.getCurrentUser();
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Cart::getUserId,user.getId())
                .eq(Cart::getSkuId,skuId);
        Cart cart = cartMapper.selectOne(wrapper);
        return cart;

    }

}
