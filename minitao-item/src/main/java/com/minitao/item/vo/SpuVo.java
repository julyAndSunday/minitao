package com.minitao.item.vo;

import com.minitao.item.entity.Sku;
import com.minitao.item.entity.Spu;
import lombok.Data;

import java.util.List;

@Data
public class SpuVo extends Spu {

    private String categoryName;

    private String brandName;

    private List<Sku> skus;
}
