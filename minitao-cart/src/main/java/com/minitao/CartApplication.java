package com.minitao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description:
 * @Author: XJ
 * @Date: 2020-07-09 09:24
 **/
@SpringBootApplication
@MapperScan("com.minitao.cart.mapper")
@EnableFeignClients(basePackages = "com.minitao.feign")
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class);
    }
}
