package com.item.common.vo;

import lombok.Data;

/**
 * @Description:
 * @Author: july
 * @Date: 2020-06-18 20:52
 **/
@Data
public class SpuParamVo {
    private Long id;
    private Long spuId;
    private String name;
    private String value;
    private boolean isSelect;

}
