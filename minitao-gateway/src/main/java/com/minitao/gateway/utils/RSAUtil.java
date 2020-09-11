package com.minitao.gateway.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Properties;

public class RSAUtil {


    private static final Logger LOGGER = LoggerFactory.getLogger(RSAUtil.class);
    /**
     * 算法名称
     */
    private static final String ALGORITHM = "RSA";
    /**
     * RSA签名算法
     */
    private static final String RSA_SIGNATURE_ALGORITHM = "SHA256WithRSA";
    /**
     * 默认密钥大小
     */
    private static final int KEY_SIZE = 2048;
    /**
     * 最大解密长度
     */
    private static final int MAX_DECRYPT_BLOCK = 256;

    private static KeyStore keyStore = null;
    /**
     * 缓存的密钥对
     */
    private static KeyPair keyPair = null;

    private static PublicKey publicKey;
    private static PrivateKey privateKey;
    /**
     * Base64 编码/解码器 JDK1.8
     */
    private static Base64.Decoder decoder = Base64.getDecoder();
    private static Base64.Encoder encoder = Base64.getEncoder();

    /** 初始化密钥工厂 */
    static {
        try {
            keyStore = KeyStore.getInstance("jks");
            File file = new File("D:\\IDEA\\IdeaProjects\\minitaoPro\\minitao\\minitao-auth\\src\\main\\resources\\my.jks");
            keyStore.load(new FileInputStream(file), "password".toCharArray());
            publicKey = keyStore.getCertificate("jwt").getPublicKey();
            privateKey = (PrivateKey) keyStore.getKey("jwt", "password".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 私有构造器
     */
    private RSAUtil() {
    }


    /**
     * 获取密钥字符串
     *
     * @param keyName    需要获取的密钥名
     * @param properties 密钥文件
     * @return Base64编码的密钥字符串
     */
    private static String getKeyString(String keyName, Properties properties) {
        return properties.getProperty(keyName);
    }

    /**
     * 从文件获取RSA公钥
     *
     * @return RSA公钥
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicKey() {
     return publicKey;
    }

    /**
     * 从文件获取RSA私钥
     *
     * @return RSA私钥
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateKey() {
      return privateKey;
    }

    /**
     * RSA公钥加密
     */
    public static byte[] encryptByPublicKey(byte[] data) throws Exception {
        // 对数据加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey());
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > 117) {
                cache = cipher.doFinal(data, offSet, 117);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * 117;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    //私钥解密
    public static byte[] decryptByPrivateKey(byte[] encryptedData) throws Exception {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(getPrivateKey().getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * RSA公钥验签
     *
     * @param data 待签名字符串
     *             公钥（Base64编码）
     * @return 验签结果
     * @throws Exception
     */
    public static boolean verify(byte[] data, String sign) throws Exception {
        Signature signature = Signature.getInstance(RSA_SIGNATURE_ALGORITHM);
        signature.initVerify(getPublicKey());
        signature.update(data);
        return signature.verify(Base64Utils.decodeFromUrlSafeString(sign));
    }

    /**
     * RSA私钥签名：签名方式SHA1withRSA
     *
     * @param data 待签名byte[]
     *             私钥（Base64编码）
     * @return 签名byte[]
     * @throws Exception
     */
    public static byte[] sign(byte[] data) throws Exception {
        // Sign
        Signature signature = Signature.getInstance(RSA_SIGNATURE_ALGORITHM);
        signature.initSign(getPrivateKey());
        signature.update(data);
        return signature.sign();
    }


    /**
     * 将char转换为byte
     *
     * @param c char
     * @return byte
     */
    private static byte toByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


    public static int getValidLength(byte[] bytes) {
        int i = 0;
        if (null == bytes || 0 == bytes.length)
            return i;
        for (; i < bytes.length; i++) {
            if (bytes[i] == '\0')
                break;
        }
        return i + 1;
    }

    public static KeyPair GetKeyPair() {
        return new KeyPair(getPublicKey(), getPrivateKey());
    }

}