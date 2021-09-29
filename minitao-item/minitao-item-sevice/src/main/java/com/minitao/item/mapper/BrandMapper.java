package com.minitao.item.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.item.common.entity.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper extends BaseMapper<Brand> {
    @Insert("insert into tao_brand_category (category_id,brand_id) values(#{cid},#{bid})")
    void insertCategoryAndBrand(@Param("cid") Long cid, @Param("bid") Long bid);

    @Select("select * from tao_brand b INNER JOIN tao_brand_category c on b.id = c.brand_id where c.category_id = #{cid}")
    List<Brand> selectBrandsByCid(@Param("cid") Long cid);
}
