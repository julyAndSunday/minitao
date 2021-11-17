package com.minitao.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minitao.order.entity.Receive;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReceiveMapper  extends BaseMapper<Receive> {
    List<Receive> selectByUserId(@Param("id") Long id);
}