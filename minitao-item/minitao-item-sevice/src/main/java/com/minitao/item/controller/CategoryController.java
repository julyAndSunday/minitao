package com.minitao.item.controller;

import com.item.common.entity.Category;
import com.minitao.common.response.CommonResult;
import com.minitao.common.response.ResultCode;
import com.minitao.item.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Api(tags = "分类接口")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父节点id查询子节点
     * @param pid
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询子分类")
    public CommonResult<List<Category>> queryCategoryByPid(@RequestParam(value = "pid",defaultValue = "0") Long pid){
            if(pid==null || pid<0){
                //400 请求参数异常
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                return CommonResult.failed(ResultCode.VALIDATE_FAILED);
            }
            List<Category> categories = categoryService.queryCategoryByPid(pid);
            if(CollectionUtils.isEmpty(categories)){
                //404 请求资源不存在
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                return CommonResult.failed(ResultCode.FAILED);
            }
            //200
            return CommonResult.success(categories);
    }

    @GetMapping("/bid/{bid}")
    @ApiOperation("查询品牌分类")
    public ResponseEntity<List<Category>> queryCategoryByBid(@PathVariable("bid")Long bid){
        if(bid==null || bid<0){
            return ResponseEntity.badRequest().build();
        }
        List<Category> categories = categoryService.queryCategoryByBid(bid);
        if(CollectionUtils.isEmpty(categories)){
            return ResponseEntity.notFound().build();
        }
        //200
        return ResponseEntity.ok(categories);
    }

    @ApiOperation("根据分类id查询分类名称")
    @GetMapping("/name")
    public ResponseEntity<List<String>> queryNamesByIds(@RequestParam("ids")List<Long>ids){
        List<String> names = categoryService.queryNameByIds(ids);

        if(CollectionUtils.isEmpty(names)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(names);
    }
}
