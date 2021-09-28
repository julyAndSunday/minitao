package com.minitao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:
 * @Author: july
 * @Date: 2020-07-09 09:24
 **/
@SpringBootApplication
@ComponentScan("com.minitao.common")
@MapperScan("com.minitao.cart.mapper")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.minitao.cart.rpc")
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class);
    }
}
