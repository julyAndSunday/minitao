package com.minitao.cart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:
 * @Author: july
 * @Date: 2020-07-09 09:24
 **/
@SpringBootApplication(scanBasePackages = "com.minitao")
@MapperScan("com.minitao.cart.mapper")
@EnableDiscoveryClient
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class);
    }
}
