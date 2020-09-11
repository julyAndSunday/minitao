package com.minitao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description:
 * @Author: july
 * @Date: 2020-07-09 09:24
 **/
@SpringBootApplication
@MapperScan("com.minitao.cart.mapper")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.minitao.cart.rpc")
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class);
    }
}
