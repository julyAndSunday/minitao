package com.minitao.cart.controller;


import com.minitao.cart.entity.Cart;
import com.minitao.cart.entity.User;
import com.minitao.cart.service.CartService;
import com.minitao.common.response.CommonResult;
import com.minitao.feign.UserFeign;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/all")
    public CommonResult getCarts(){
        List<Cart> carts = cartService.getCarts();
        if (CollectionUtils.isEmpty(carts)){
            return CommonResult.failed();
        }
        return CommonResult.success(carts);
    }


    @GetMapping("/test")
    public void test() {
        System.out.println("哈哈哈");
    }

}

