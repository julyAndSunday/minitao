package com.minitao.item.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.minitao.common.response.CommonResult;
import com.minitao.item.entity.Brand;
import com.minitao.item.entity.Category;
import com.minitao.item.entity.Sku;
import com.minitao.item.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description:
 * @Author: XJ
 * @Date: 2020-06-02 08:58
 **/
@RestController
@RequestMapping("/brand")
@Api(tags = "品牌接口")
public class BrandController {

    @Autowired
    private BrandService brandService;


    @GetMapping("/page")
    @ApiOperation("品牌查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "查询关键字", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "当前页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每页条数", paramType = "query", dataType = "int")
    })
    public CommonResult<IPage<Brand>> queryBrandByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc
    ) {
        IPage<Brand> brandIPage = this.brandService.queryBrandByPage(key, page, rows, sortBy, desc);
        if (CollectionUtils.isEmpty(brandIPage.getRecords())) {
            return CommonResult.failed();
        }
        return CommonResult.success(brandIPage);
    }

    @ApiOperation("根据分类查询品牌")
    @GetMapping("/cid/{cid}")
    public CommonResult<List<Brand>> queryBrandByCid(@PathVariable("cid") Long cid) {
        List<Brand> brands = this.brandService.queryBrandByCid(cid);
        System.out.println("查询分类下品牌");
        if (CollectionUtils.isEmpty(brands)) {
            return CommonResult.failed("该分类下没有品牌");
        }
        return CommonResult.success(brands);
    }

    @ApiOperation("根据id查询品牌")
    @GetMapping("{id}")
    public CommonResult<Brand> queryBrandById(@PathVariable("id") Long id) {
        Brand brand = this.brandService.queryBrandById(id);
        return CommonResult.success(brand);
    }

    @PostMapping
    @ApiOperation("保存品牌")
    public CommonResult<Void> saveBrand(Brand brand, @RequestParam("cid") Long cid) {
        this.brandService.saveBrand(brand, cid);
        return CommonResult.success(null);
    }


}
