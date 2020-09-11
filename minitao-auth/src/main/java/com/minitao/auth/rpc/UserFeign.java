package com.minitao.auth.rpc;

import com.minitao.auth.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("minitao-user")
public interface UserFeign {

    @GetMapping("/loadUsername")
    UserDto loadUserByUsername(@RequestParam("username") String username);



}
