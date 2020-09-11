package com.minitao.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minitao.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author july
 * @since 2020-07-23
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from tao_user where username = #{username}")
    User selectUserByName(@Param("username")String username);

    @Select("select * from tao_user where username=#{username} and password = #{password}")
    User validate(@Param("username")String username,@Param("password")String password);
}
