package com.minitao.item.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.item.common.entity.TbParam;
import com.item.common.vo.SpuParamVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: july
 * @Date: 2020-06-18 16:17
 **/
public interface ParamMapper extends BaseMapper<TbParam> {

    public List<TbParam> getParamByCid(@Param("cid") Long cid);

    List<SpuParamVo> getParamBySpuId(@Param("spuId") Long spuId);
}
