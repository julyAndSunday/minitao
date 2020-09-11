package com.minitao.item.service.impl;

import com.item.common.entity.TbParam;
import com.item.common.vo.SpuParamVo;
import com.minitao.item.mapper.ParamMapper;
import com.minitao.item.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: july
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
