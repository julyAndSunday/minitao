package com.minitao.item.controller;

import com.item.common.entity.TbParam;
import com.item.common.vo.SpuParamVo;
import com.minitao.common.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.minitao.item.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: july
 * @Date: 2020-06-18 16:23
 **/
@RestController
@RequestMapping("/item/param")
@Api(tags = "参数接口")
public class ParamController {

    @Autowired
    private ParamService paramService;

    @ApiOperation("查询分类下所有参数")
    @GetMapping("/cid/{cid}")
    public CommonResult<List<TbParam>> getParamByCid(@PathVariable("cid")Long cid){
        List<TbParam> params = this.paramService.getParamByCid(cid);
        if (CollectionUtils.isEmpty(params)){
            return CommonResult.failed();
        }
        return CommonResult.success(params);
    }

    @ApiOperation("查询spu的参数名称和值")
    @GetMapping("spuId/{spuId}")
    public CommonResult<List<SpuParamVo>> getParamBySpuId(@PathVariable("spuId")Long spuId){
        List<SpuParamVo> spuParamVoList = this.paramService.getParamBySpuId(spuId);
        if (CollectionUtils.isEmpty(spuParamVoList)){
            return CommonResult.failed();
        }
        return CommonResult.success(spuParamVoList);
    }

}
