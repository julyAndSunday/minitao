package com.minitao.item.controller;

import com.aliyun.oss.internal.OSSUtils;
import com.minitao.common.response.CommonResult;
import com.minitao.item.utils.AliyunOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-11-15 17:08
 **/
@RestController
@RequestMapping("/item/file")
public class FileController {
    @Autowired
    private AliyunOSSUtil ossUtils;

    @PostMapping("/upload")
    public CommonResult upload(@RequestParam("file") MultipartFile file){
        String path = ossUtils.upload(file);
        return CommonResult.success(path);
    }

}
