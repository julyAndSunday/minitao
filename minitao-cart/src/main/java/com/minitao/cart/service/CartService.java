package com.minitao.cart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minitao.cart.entity.Cart;

import java.util.List;

/**
 *
 * @author july
 * @since 2020-07-21
 */
public interface CartService {

    void add(Cart cart);

    List<Cart> getCarts();
}
