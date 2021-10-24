package com.minitao.item.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.item.common.dto.StockDto;
import com.item.common.entity.Sku;
import com.item.common.entity.Spu;
import com.item.common.vo.SkuVo;
import com.item.common.vo.SpuVo;

import java.util.List;

public interface GoodsService {


    /**
     * 分页查询spu
     *
     * @param key
     * @param page
     * @param rows
     * @return
     */
    public IPage<SpuVo> queryByPage(String key, Integer page, Integer rows);


    /**
     * 更新商品
     *
     * @param spuVo
     */
    public void updateBrand(SpuVo spuVo);

    public Spu querySpuById(Long id);

    public Sku querySkuById(Long id);


    public List<SkuVo> querySkusBySpuId(Long id);

    void subtractStock(List<Integer> skuId, List<Integer> count);

    boolean seckill(int skuId);

    void seckill_cas(int skuId);


    void putSeckillStore(List<Integer> skuId, List<Integer> count);

    List<Spu> querySpuByCid3(int cid);
}
