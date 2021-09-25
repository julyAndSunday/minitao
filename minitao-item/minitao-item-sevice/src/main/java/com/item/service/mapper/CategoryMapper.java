package com.item.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.item.common.entity.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {
    @Select("select * from tao_category c inner join tao_brand_category bc on" +
            " bc.category_id = c.id where bc.brand_id = #{bid}")
    List<Category> queryCidByBid(@Param("bid") Long bid);
}
