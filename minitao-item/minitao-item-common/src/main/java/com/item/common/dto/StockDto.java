package com.item.common.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-09-24 21:38
 **/
@Data
public class StockDto {
    private List<Integer> skuId;
    private List<Integer> count;

    public StockDto(List<Integer> skuId, List<Integer> count) {
        this.skuId = skuId;
        this.count = count;
    }
}
