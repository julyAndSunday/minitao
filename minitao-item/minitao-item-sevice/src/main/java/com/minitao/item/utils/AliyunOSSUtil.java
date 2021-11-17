package com.minitao.item.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectMetadata;
import com.minitao.item.config.OssConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class AliyunOSSUtil{


    @Autowired
    private OSS ossClient;
    @Autowired
    private OssConfig OssConfig;


    public String upload(MultipartFile uploadFile) {
        // 获取oss的Bucket名称
        String bucketName = OssConfig.getBucketName();
        // 获取oss的地域节点
        String endpoint = OssConfig.getEndPoint();
        // 获取oss的AccessKeySecret
        String accessKeySecret = OssConfig.getAccessKeySecret();
        // 获取oss的AccessKeyId
        String accessKeyId = OssConfig.getAccessKeyId();
        String returnImgeUrl = "";
        //文件新路径
        String fileName = uploadFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf("."));
        //1 在文件名称里面添加随机唯一的值
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        // yuy76t5rew01.jpg
        fileName = uuid+ext;

        // 上传到阿里云
        // 获取文件输入流
        InputStream in = null;
        ObjectMetadata meta = null;
        try {
            in=uploadFile.getInputStream();
            meta = getObjectMetadata(in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ossClient.putObject(bucketName, fileName, in,meta);
        System.out.println(OssConfig.getUrlPrefix());
        returnImgeUrl = "http://" + bucketName + "." + endpoint + "/" + fileName;
        return returnImgeUrl;
    }


    public List<String> uploads(MultipartFile[] file) {

        // 获取oss的Bucket名称
        String bucketName = OssConfig.getBucketName();
        // 获取oss的地域节点
        String endpoint = OssConfig.getEndPoint();
        List<String> pathList = new ArrayList<>();
        for (MultipartFile uploadFile : file) {
            String fileName = uploadFile.getOriginalFilename();
//            String filePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            String uploadImgeUrl ="seconds" + "/" + fileName;

            InputStream inputStream = null;
            try {
                inputStream=uploadFile.getInputStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType("image/jpg");
            ossClient.putObject(bucketName, uploadImgeUrl, inputStream,meta);
        }

        return pathList;
    }

    private static ObjectMetadata getObjectMetadata(long length) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(length);
        // 被下载时网页的缓存行为
        objectMetadata.setCacheControl("no-cache");
        objectMetadata.setHeader("Pragma", "no-cache");
        return objectMetadata;
    }
}


