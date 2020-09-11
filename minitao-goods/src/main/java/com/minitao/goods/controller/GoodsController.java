package com.minitao.goods.controller;

import com.item.common.vo.SpuVo;
import com.minitao.common.response.CommonResult;
import com.minitao.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: July
 * @Date: 2020-09-10 17:00
 **/
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/spu/{spuId}")
    public CommonResult getSpuBySpuId(@PathVariable("spuId")Long spuId){
        SpuVo spuVo = goodsService.getSpuBySpuId(spuId);
        return CommonResult.success(spuVo);

    }
}
