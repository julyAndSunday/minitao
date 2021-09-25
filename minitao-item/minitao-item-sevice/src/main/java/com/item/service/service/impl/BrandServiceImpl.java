package com.item.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.item.common.entity.Brand;
import com.item.service.mapper.BrandMapper;
import com.item.service.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: XJ
 * @Date: 2020-06-02 09:46
 **/
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;


    public IPage<Brand> queryBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        IPage<Brand> brandIpage = new Page<>(page, rows);
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Brand::getName, key);
        if (StringUtils.isNotBlank(sortBy)) {
            if (desc) {
                queryWrapper.orderByDesc(sortBy);
            } else {
                queryWrapper.orderByAsc(sortBy);
            }
        }
        IPage<Brand> brandPage = brandMapper.selectPage(brandIpage, queryWrapper);
        return brandPage;
    }


    @Transactional()
    public void saveBrand(Brand brand, Long cid) {
        //新增brand
        brand.setCreateTime(new Date());
        this.brandMapper.insert(brand);

        //新增中间表
        this.brandMapper.insertCategoryAndBrand(cid, brand.getId());

    }



    public List<Brand> queryBrandByCid(Long cid) {
        return this.brandMapper.selectBrandsByCid(cid);

    }

    public Brand queryBrandById(Long id) {
        return this.brandMapper.selectById(id);
    }


}

