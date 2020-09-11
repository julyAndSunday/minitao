package com.minitao.gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minitao.gateway.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author july
 * @since 2020-07-23
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select id from tao_user where username = #{username}")
    Long selectUserIdByName(@Param("username") String username);

}
