package com.minitao.item.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.minitao.item.vo.SkuVo;
import com.minitao.item.vo.SpuVo;
import com.minitao.item.entity.Sku;
import com.minitao.item.entity.Spu;

import java.util.*;

public interface GoodsService {


    /**
     * 分页查询spu
     *
     * @param key
     * @param page
     * @param rows
     * @return
     */
    public IPage<SpuVo> queryByPage(String key, Integer page, Integer rows) ;



    /**
     * 更新商品
     *
     * @param spuVo
     */
    public void updateBrand(SpuVo spuVo) ;

    public Spu querySpuById(Long id);

    public Sku querySkuById(Long id);


    public List<SkuVo> querySkusBySpuId(Long id);
}
