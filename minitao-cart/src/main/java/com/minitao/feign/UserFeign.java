package com.minitao.feign;

import com.minitao.cart.entity.User;
import com.minitao.common.response.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("minitao-user")
public interface UserFeign {

    @GetMapping("/current")
    User getCurrentUser();
}
