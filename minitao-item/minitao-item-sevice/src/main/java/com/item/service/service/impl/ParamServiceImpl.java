package com.item.service.service.impl;

import com.item.common.entity.TbParam;
import com.item.common.vo.SpuParamVo;
import com.item.service.mapper.ParamMapper;
import com.item.service.service.ParamService;
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
