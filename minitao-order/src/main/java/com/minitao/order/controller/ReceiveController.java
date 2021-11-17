package com.minitao.order.controller;

import com.minitao.common.annotation.CurrentUser;
import com.minitao.common.entity.User;
import com.minitao.common.response.CommonResult;
import com.minitao.order.entity.Receive;
import com.minitao.order.service.ReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-11-13 21:21
 **/
@RestController
@RequestMapping("/order")
public class ReceiveController {

    @Autowired
    private ReceiveService receiveService;

    @PostMapping("/addReceive")
    public CommonResult addReceive(@RequestBody Receive receive, @CurrentUser User user){
        receiveService.addReceive(receive,user);
        return CommonResult.success(receive);
    }

    @PostMapping("/deleteReceive")
    public CommonResult deleteReceive(@RequestBody Receive receive){
        receiveService.deleteReceive(receive);
        return CommonResult.success(receive);
    }

    @PostMapping("/updateReceive")
    public CommonResult updateReceive(@RequestBody Receive receive){
        receiveService.updateReceive(receive);
        return CommonResult.success(receive);
    }

    @GetMapping("/listReceive")
    public CommonResult getMyReceives(@CurrentUser User user){
       List<Receive> receives =  receiveService.getMyReceives(user);
        return CommonResult.success(receives);
    }

}
