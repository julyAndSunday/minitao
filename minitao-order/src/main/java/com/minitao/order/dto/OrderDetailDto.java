package com.minitao.order.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-09-24 19:06
 **/
@Data
public class OrderDetailDto {
    private List<Integer> skuId;
    private List<Integer> count;
    private List<Double> price;

}
