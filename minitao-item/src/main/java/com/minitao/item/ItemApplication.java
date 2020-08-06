package com.minitao.item;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:商家后台管理模块
 * @Author: XJ
 * @Date: 2020-06-01 23:08
 **/

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.minitao.item.mapper")
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class);
    }
}
