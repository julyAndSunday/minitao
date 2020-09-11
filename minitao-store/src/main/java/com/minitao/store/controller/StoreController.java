package com.minitao.store.controller;


import com.item.common.vo.SpuVo;
import com.minitao.common.response.CommonResult;
import com.minitao.store.entity.Store;
import com.minitao.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/addSpu")
    public CommonResult addSpu(@RequestBody SpuVo spuVo){
       storeService.addSpu(spuVo);
        return CommonResult.success(spuVo);
    }

    @PostMapping("/deleteSpu/{spuId}")
    public CommonResult deleteSpu(@PathVariable("spuId")Long spuId){
        int result = storeService.deleteSpu(spuId);
        return CommonResult.success(null);
    }

}

