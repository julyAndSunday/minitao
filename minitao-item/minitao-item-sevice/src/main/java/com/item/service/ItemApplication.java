package com.item.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:商家后台管理模块
 * @Author: july
 * @Date: 2020-06-01 23:08
 **/

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.minitao")
@ComponentScan("com.minitao.common")
@MapperScan("com.item.service.mapper")
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class);
    }
}
