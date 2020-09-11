package com.minitao.cart.controller;


import com.minitao.cart.entity.Cart;
import com.minitao.cart.service.CartService;
import com.minitao.common.response.CommonResult;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author july
 * @since 2020-07-21
 */
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public CommonResult add(@RequestBody Cart cart) {
        cartService.add(cart);
        return CommonResult.success(cart);
    }

//    @GetMapping("/all")
//    public CommonResult getCarts() {
//        List<Cart> carts = cartService.getCarts();
//        if (CollectionUtils.isEmpty(carts)) {
//            return CommonResult.failed();
//        }
//        return CommonResult.success(carts);
//    }


    @GetMapping("/test")
    public void test(HttpServletRequest request) {
        String userInfo = request.getHeader("UserInfo");
        System.out.println(userInfo);

    }

}

