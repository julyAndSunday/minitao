package com.minitao;
import com.minitao.item.ItemApplication;
import com.minitao.item.vo.SkuVo;
import com.minitao.item.entity.Brand;
import com.minitao.item.service.BrandService;
import com.minitao.item.service.CategoryService;
import com.minitao.item.service.GoodsService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description:
 * @Author: XJ
 * @Date: 2020-06-03 14:47
 **/
@SpringBootTest(classes = ItemApplication.class)
@RunWith(SpringRunner.class)
public class Test {
    @Autowired
    BrandService brandService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    GoodsService goodsService;

    @org.junit.Test
    public void test() {


        Brand brand = new Brand();
        brand.setName("荣耀");
        brandService.saveBrand(brand,6l);

//        List<Category> categories = categoryService.queryCategoryByBid(2l);
//        categories.forEach(category -> System.out.println(categories));
//        List<Long> cids = new ArrayList<>();
//        cids.add(1l);
//        cids.add(2l);
//        cids.add(5l);
//        List<String> strings = categoryService.queryNameByIds(cids);
//        System.out.println(strings);


    }

    @org.junit.Test
    public void test2() {
        List<SkuVo> skuVos = goodsService.querySkusBySpuId(1l);
        skuVos.forEach(System.out::println);

    }
}