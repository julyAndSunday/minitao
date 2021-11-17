package com.minitao.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.item.common.entity.Category;
import com.minitao.item.mapper.CategoryMapper;
import com.minitao.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 根据父节点id查询子节点
     * @param pid
     * @return
     */
    public List<Category> queryCategoryByPid(Long pid) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Category::getParentId,pid);
        return this.categoryMapper.selectList(queryWrapper);
    }

    /**
     * 根据ids获取商品具体分类
     * @param ids
     * @return
     */
    public List<String> queryNameByIds(List<Long> ids){
        List<Category> categories = categoryMapper.selectBatchIds(ids);
        return categories.stream().map(category -> category.getName())
                .collect(Collectors.toList());

    }

    public List<Category> queryCategoryByBid(Long bid) {
        return this.categoryMapper.queryCidByBid(bid);
    }
}
