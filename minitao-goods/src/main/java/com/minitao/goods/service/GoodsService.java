package com.minitao.goods.service;

import com.item.common.vo.SpuVo;
import com.minitao.goods.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;

public interface GoodsService {


    SpuVo getSpuBySpuId(Long spuId);
}
