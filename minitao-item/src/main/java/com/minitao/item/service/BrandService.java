package com.minitao.item.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.minitao.item.entity.Brand;

import java.util.List;

/**
 * @Description:
 * @Author: XJ
 * @Date: 2020-06-02 09:46
 **/
public interface BrandService {


     IPage<Brand> queryBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc);

     void saveBrand(Brand brand, Long cid);

     List<Brand> queryBrandByCid(Long cid);

     Brand queryBrandById(Long id);

}

