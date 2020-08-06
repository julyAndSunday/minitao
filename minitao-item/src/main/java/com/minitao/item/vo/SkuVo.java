package com.minitao.item.vo;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @Description:
 * @Author: XJ
 * @Date: 2020-06-20 15:13
 **/
@Data
public class SkuVo {
    private Long id;
    private Long spuId;
    private String title;
    private String images;
    private Long price;
    private Date createTime;// 创建时间
    private Integer stock;// 库存
    private Map<String, String> paramValue;
}
