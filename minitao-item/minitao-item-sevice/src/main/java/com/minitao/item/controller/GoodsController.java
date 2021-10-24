package com.minitao.item.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.item.common.dto.StockDto;
import com.item.common.entity.Spu;
import com.item.common.vo.SkuVo;
import com.item.common.vo.SpuVo;
import com.minitao.common.response.CommonResult;
import com.minitao.item.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@Api(tags = "商品接口")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;


    @ApiOperation("搜索商品集")
    @GetMapping("/spu/page")
    public CommonResult<IPage<SpuVo>> queryByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows
    ) {
        IPage<SpuVo> spuBos = goodsService.queryByPage(key, page, rows);
        if (CollectionUtils.isEmpty(spuBos.getRecords())) {
            return CommonResult.failed();
        }
        return CommonResult.success(spuBos);
    }

    @GetMapping("/spuUnderCid/{cid}")
    public CommonResult querySpuByCid3(int cid){
        List<Spu> spus = goodsService.querySpuByCid3(cid);
        return CommonResult.success(spus);
    }

    @ApiOperation("查询一个商品集")
    @GetMapping("/spu/{spuId}")
    public CommonResult<List<SkuVo>> querySkusBySpuId(@PathVariable("spuId") Long spuId) {
        List<SkuVo> skuVos = goodsService.querySkusBySpuId(spuId);
        if (CollectionUtils.isEmpty(skuVos)) {
            return CommonResult.failed();
        }
        return CommonResult.success(skuVos);
    }

    @ApiOperation("减库存")
    @PostMapping("/stock/subtract")
    public CommonResult subtractStock(@RequestBody StockDto stockDto) throws InterruptedException {
//        Thread.sleep(2000); //测试seata
        goodsService.subtractStock(stockDto.getSkuId(), stockDto.getCount());
        return CommonResult.success(null);
    }

    @ApiOperation("放入秒杀库存")
    @PostMapping("/putSeckillStore")
    public CommonResult putSeckillStore(@RequestBody StockDto stockDto) {
        goodsService.putSeckillStore(stockDto.getSkuId(), stockDto.getCount());
        return CommonResult.success(null);
    }

    @PostMapping("/seckill")
    public CommonResult seckill(@RequestParam int skuId) {
        boolean seckill = goodsService.seckill(skuId);
        if (seckill) {
            return CommonResult.success(skuId);
        } else {
            return CommonResult.failed("秒杀失败");
        }
    }

}
