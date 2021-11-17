package com.minitao.cart.service;

import com.minitao.cart.entity.Cart;
import com.minitao.common.annotation.CurrentUser;
import com.minitao.common.entity.User;

import java.util.List;

/**
 *
 * @author july
 * @since 2020-07-21
 */
public interface CartService {


    void add(Cart cart, User user);

    List<Cart> getCarts( User user);
}
