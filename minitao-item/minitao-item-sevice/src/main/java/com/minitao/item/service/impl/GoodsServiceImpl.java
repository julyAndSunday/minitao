package com.minitao.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.item.common.entity.Brand;
import com.item.common.entity.Sku;
import com.item.common.entity.Spu;
import com.item.common.vo.SkuVo;
import com.item.common.vo.SpuParamVo;
import com.item.common.vo.SpuVo;
import com.minitao.item.mapper.BrandMapper;
import com.minitao.item.mapper.ParamMapper;
import com.minitao.item.mapper.SkuMapper;
import com.minitao.item.service.CategoryService;
import com.minitao.item.service.GoodsService;
import com.minitao.item.mapper.SpuMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private ParamMapper paramMapper;

    @Autowired
    private CategoryService categoryService;

    private static final ObjectMapper MAPPER = new ObjectMapper();


    /**
     * 分页查询spu
     *
     * @param key
     * @param page
     * @param rows
     * @return
     */
    public IPage<SpuVo> queryByPage(String key, Integer page, Integer rows) {
        QueryWrapper<Spu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Spu::getTitle, key);
        IPage<Spu> spuIPage = new Page<>(page, rows);

        IPage<Spu> spuPage = spuMapper.selectPage(spuIPage, queryWrapper);

        List<Spu> spus = spuPage.getRecords();
        //spu转成spuBo
        List<SpuVo> spuVos = spus.stream().map(spu -> {
            SpuVo spuVo = new SpuVo();
            BeanUtils.copyProperties(spu, spuVo);

            //查询品牌名称
            Brand brand = this.brandMapper.selectById(spuVo.getBrandId());
            spuVo.setBrandName(brand.getName());

            //查询分类名称
            List<String> strings = categoryService.queryNameByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            spuVo.setCategoryName(StringUtils.join(strings, "-"));

            //查询sku
            QueryWrapper<Sku> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.lambda().eq(Sku::getSpuId, spu.getId());
            List<Sku> skus = this.skuMapper.selectList(queryWrapper1);
            spuVo.setSkus(skus);
            return spuVo;
        }).collect(Collectors.toList());

        IPage<SpuVo> spuBoIPage = new Page<>();
        BeanUtils.copyProperties(spuPage, spuBoIPage);
        spuBoIPage.setRecords(spuVos);
        log.info("根据{} 查询商品集", key);
        return spuBoIPage;
    }


    /**
     * 更新商品
     *
     * @param spuVo
     */
    @Transactional
    public void updateBrand(SpuVo spuVo) {

    }

    public Spu querySpuById(Long id) {
        return this.spuMapper.selectById(id);
    }

    public Sku querySkuById(Long id) {
        return this.skuMapper.selectById(id);
    }


    public List<SkuVo> querySkusBySpuId(Long id) {
        QueryWrapper<Sku> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Sku::getSpuId, id);
        List<Sku> skus = this.skuMapper.selectList(queryWrapper);

        List<SkuVo> skuVos = new ArrayList<>();
        //封装skuVo
        skus.forEach(sku -> {
            SkuVo skuVo = new SkuVo();
            BeanUtils.copyProperties(sku, skuVo);

            //查询sku参数
            Map<String, String> paramValueMap = new HashMap<>();
            try {
                //从spu中获取普通参数
                List<SpuParamVo> paramVos = paramMapper.getParamBySpuId(sku.getSpuId());
                paramVos.forEach(spuParamVo -> paramValueMap.put(spuParamVo.getName(), spuParamVo.getValue()));

                //从sku表中的json获取特殊参数
                //覆盖spu中多值的普通参数
                String paramJson = sku.getParamJson();
                JsonNode jsonNode = MAPPER.readTree(paramJson);
                for (int i = 0; i < jsonNode.size(); i++) {
                    String key = jsonNode.get(i).get("k").textValue();
                    String value = jsonNode.get(i).get("v").textValue();
                    paramValueMap.put(key, value);
                }
                skuVo.setParamValue(paramValueMap);
                skuVos.add(skuVo);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return skuVos;
    }
}
