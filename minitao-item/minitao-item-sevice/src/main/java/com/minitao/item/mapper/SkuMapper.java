package com.minitao.item.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.item.common.entity.Sku;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @Author: july
 * @Date: 2020-06-02 15:59
 **/
public interface SkuMapper extends BaseMapper<Sku> {
    void updateStock(@Param("skuId") int skuId, @Param("count") int count);

    void decreaseStock(@Param("skuId") int skuId);


    int selectVersion(@Param("skuId") int skuId);

    void decreaseStockByCAS(@Param("skuId") int skuId, @Param("version") int version);
}
