package com.minitao.user.utils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.QuerySendDetailsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;
import com.minitao.user.config.SmsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-11-15 17:42
 **/
public class SmsUtil {
    private Client client;
    private static final String endPoint = "dysmsapi.aliyuncs.com";
    private static final String accessKeyId = "LTAI4FpsHDjNU41Lnfopkobj";
    private static final String accessKeySecret = "wRciv2ycIUFg1wJcmviM3dh5qErgEd";
    private static final String signName = "乐优商城";
    private static final String template_code = "SMS_187746351";
    private static final String param = "{'code':'?'}";

    private volatile static SmsUtil smsUtil;

    private SmsUtil(Client client) {
        this.client = client;
    }

    public static SmsUtil instance()  {
        if (smsUtil == null) {
            synchronized (SmsUtil.class) {
                if (smsUtil == null) {
                    Config config = new Config()
                            // 您的AccessKey ID
                            .setAccessKeyId(accessKeyId)
                            // 您的AccessKey Secret
                            .setAccessKeySecret(accessKeySecret);
                    // 访问的域名
                    config.endpoint = endPoint;
                    Client client = null;
                    try {
                        client = new Client(config);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return new SmsUtil(client);
                }
            }
        }
        return smsUtil;
    }

    public void send(String phone, String msg) {
        String content = param.replace("?",msg);
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setPhoneNumbers(phone)
                .setSignName(signName)
                .setTemplateCode(template_code)
                .setTemplateParam(content);


        try {
            client.sendSms(sendSmsRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SmsUtil instance = SmsUtil.instance();
        String code = CodeGen.generate();
        System.out.println(code);
        instance.send("18320519438",code);
    }
}
