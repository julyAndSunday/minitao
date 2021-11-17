package com.minitao.user.service.impl;

import com.minitao.common.utils.RedisClient;
import com.minitao.user.service.CodeService;
import com.minitao.user.utils.CodeGen;
import com.minitao.user.utils.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-11-15 19:43
 **/
@Service
public class CodeServiceImpl implements CodeService {

    private SmsUtil smsUtil =  SmsUtil.instance();
    private final String pre = "code:";
    @Autowired
    private RedisClient redisClient;

    @Override
    public void sendCode(String phone) {
        String code = CodeGen.generate();
        redisClient.setString(pre+phone,code,60L);
        smsUtil.send(phone,code);
    }

    public boolean verifyCode(String phone,String code){
        String cacheCode = redisClient.getString(pre+phone);
        if (cacheCode!= null && cacheCode.equals(code)){
            return true;
        }
        return false;
    }
}
