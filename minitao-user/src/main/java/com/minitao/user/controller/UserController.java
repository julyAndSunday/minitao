package com.minitao.user.controller;


import com.minitao.common.response.CommonResult;
import com.minitao.user.dto.UserRequest;
import com.minitao.user.entity.User;
import com.minitao.user.service.UserService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author july
 * @since 2020-07-23
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private static int num;

    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "username", value = "用户名"),
                    @ApiImplicitParam(name = "password", value = "密码")})
    @PostMapping("/login")
    public CommonResult login(@RequestBody UserRequest userRequest) {
        if (StringUtils.isBlank(userRequest.getUsername()) || StringUtils.isBlank(userRequest.getPassword())) {
            return CommonResult.failed("用户名或密码为空");
        }
        String token = userService.login(userRequest);
        if (token == null){
            return CommonResult.failed("用户名或密码错误");
        }
        return CommonResult.success(token);
    }

    @ApiOperation(value = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码"),
            @ApiImplicitParam(name ="nickname",value = "昵称")
    })
    @PostMapping("/register")
    public CommonResult register(@RequestBody UserRequest userRequest) {
        if (StringUtils.isBlank(userRequest.getUsername()) || StringUtils.isBlank(userRequest.getPassword())) {
            return CommonResult.failed("用户名或密码为空");
        }
        User user = userService.register(userRequest);
        if (user == null) {
            return CommonResult.failed("用户名已存在");
        }
        return CommonResult.success(user);
    }

    @GetMapping("/refresh")
    public CommonResult refreshToken(@RequestParam("token") String token) {
        String newToken = userService.refreshToken(token);
        if (newToken == null) {
            return CommonResult.failed("token已过期");
        }
        return CommonResult.success(newToken);
    }

    @GetMapping("/test")
    public CommonResult hello() {
        System.out.println(num++);
        return CommonResult.success("hello");
    }

    @GetMapping("/currentUser")
    public CommonResult getCurrentUser(String token){
        User user = userService.getCurrentUser(token);
        return CommonResult.success(user);
    }

}

