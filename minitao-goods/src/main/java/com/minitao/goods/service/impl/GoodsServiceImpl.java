package com.minitao.goods.service.impl;

import com.item.common.vo.SpuVo;
import com.minitao.goods.mapper.GoodsMapper;
import com.minitao.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: July
 * @Date: 2020-09-10 17:06
 **/
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public SpuVo getSpuBySpuId(Long spuId) {
        return g
    }
}
