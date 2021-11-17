package com.minitao.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-10-23 21:12
 **/
@SpringBootTest
public class Test {

    @Autowired
    private RedisTemplate redisTemplate;

    @org.junit.jupiter.api.Test
    public void test(){
        Set keys = redisTemplate.keys("*");
        System.out.println(keys.size());
    }
}
