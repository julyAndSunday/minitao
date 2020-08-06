package com.minitao.item.service;

import com.minitao.item.entity.Category;

import java.util.List;

public interface CategoryService {

    /**
     * 根据父节点id查询子节点
     *
     * @param pid
     * @return
     */
    public List<Category> queryCategoryByPid(Long pid);

    /**
     * 根据ids获取商品具体分类
     *
     * @param ids
     * @return
     */
    public List<String> queryNameByIds(List<Long> ids);

    public List<Category> queryCategoryByBid(Long bid);
}