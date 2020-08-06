package com.minitao.item.service.impl;

import com.minitao.item.vo.SpuParamVo;
import com.minitao.item.mapper.ParamMapper;
import com.minitao.item.entity.TbParam;
import com.minitao.item.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: XJ
 * @Date: 2020-06-18 16:25
 **/
@Service
public class ParamServiceImpl implements ParamService {

    @Autowired
    private ParamMapper paramMapper;

    public List<TbParam> getParamByCid(Long cid) {
        return paramMapper.getParamByCid(cid);
    }

    public List<SpuParamVo> getParamBySpuId(Long spuId) {
        return paramMapper.getParamBySpuId(spuId);
    }
}
