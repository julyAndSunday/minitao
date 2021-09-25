package com.item.service.service;

import com.item.common.entity.TbParam;
import com.item.common.vo.SpuParamVo;

import java.util.List;

/**
 * @Description:
 * @Author: XJ
 * @Date: 2020-06-18 16:25
 **/
public interface ParamService {

    List<TbParam> getParamByCid(Long cid);

    List<SpuParamVo> getParamBySpuId(Long spuId);
}
