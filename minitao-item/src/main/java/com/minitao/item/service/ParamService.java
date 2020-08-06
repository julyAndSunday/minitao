package com.minitao.item.service;

import com.minitao.item.vo.SpuParamVo;
import com.minitao.item.entity.TbParam;

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
