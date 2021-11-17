package com.minitao.cart.mapper;

import com.minitao.cart.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author july
 * @since 2020-07-21
 */
public interface CartMapper extends BaseMapper<Cart> {

    List<Cart> selectCartByUserId(@Param("id") Long id);
}
