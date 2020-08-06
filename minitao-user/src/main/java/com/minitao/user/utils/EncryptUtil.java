package com.minitao.user.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Description:
 * @Author: July
 * @Date: 2020-07-30 09:39
 **/
@Component
public class EncryptUtil {
    private static final String transfomation = "DES";
    private static final String algorithm = "DES";
    @Value("${taoEncrypt.key}")
    private String key;

    public  String encrypt(String password){
        byte[] bytes = new byte[0];
        try {
            //获取加密对象
            Cipher cipher = Cipher.getInstance(transfomation);

            //创建加密规则
            //参数  key的字节数组 ，加密算法
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);

            //初始化加密模式和算法
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            bytes = cipher.doFinal(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return String.valueOf(Base64.encode(bytes));
    }

    public String decrypt(String password) {

        //获取加密对象
        Cipher cipher = null;
        byte[] bytes = new byte[0];
        try {
            cipher = Cipher.getInstance(transfomation);
            //创建加密规则
            //参数  key的字节数组 ，加密算法
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);

            //初始化加密模式和算法
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

             bytes = cipher.doFinal(Base64.decode(password));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }
}
