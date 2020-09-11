package com.minitao.item.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.item.common.vo.SkuVo;
import com.item.common.vo.SpuVo;
import com.minitao.common.response.CommonResult;
import com.minitao.item.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "商品接口")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @ApiOperation("搜索商品集")
    @GetMapping("/spu/page")
    public CommonResult<IPage<SpuVo>> queryByPage(
            @RequestParam(value = "key",required = false) String key,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows
            ){
        IPage<SpuVo> spuBos = this.goodsService.queryByPage(key,page,rows);
        if(CollectionUtils.isEmpty(spuBos.getRecords())){
            return CommonResult.failed();
        }
        return CommonResult.success(spuBos);
    }

    @ApiOperation("查询一个商品集")
    @GetMapping("/spu/{id}")
    public CommonResult<List<SkuVo>> querySkusBySpuId(@PathVariable("id")Long id){
        List<SkuVo> skuVos = this.goodsService.querySkusBySpuId(id);
        if(CollectionUtils.isEmpty(skuVos)){
            return CommonResult.failed();
        }
        return CommonResult.success(skuVos);
    }


}
