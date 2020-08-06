package com.minitao.item.vo;

import lombok.Data;

/**
 * @Description:
 * @Author: XJ
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
