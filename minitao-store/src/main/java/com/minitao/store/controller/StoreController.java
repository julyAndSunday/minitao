package com.minitao.store.controller;


import com.minitao.common.response.CommonResult;
import com.minitao.store.entity.Store;
import com.minitao.store.entity.User;
import com.minitao.store.feign.UserFeign;
import com.minitao.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author July
 * @since 2020-08-06
 */
@RestController
public class StoreController {
    @Autowired
    private StoreService storeService;


    @PostMapping("/register")
    public CommonResult registerStore(@RequestBody Store store) {
        Store newstore = storeService.registerStore(store);
        return CommonResult.success(newstore);
    }

}

