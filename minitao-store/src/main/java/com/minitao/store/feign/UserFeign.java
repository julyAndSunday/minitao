package com.minitao.store.feign;

import com.minitao.store.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("minitao-user")
public interface UserFeign {

    @GetMapping("/current")
    User getCurrentUser();
}
