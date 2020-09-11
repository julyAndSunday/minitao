package com.item.common.vo;

import com.item.common.entity.Sku;
import com.item.common.entity.Spu;
import lombok.Data;

import java.util.List;

@Data
public class SpuVo extends Spu {

    private String categoryName;

    private String brandName;

    private List<Sku> skus;
}
