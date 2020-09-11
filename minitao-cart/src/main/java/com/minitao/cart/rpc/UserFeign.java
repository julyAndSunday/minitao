package com.minitao.cart.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("minitao-user")
public interface UserFeign{

    @GetMapping("/currentId")
    Long getCurrentUserId(String token);
}
