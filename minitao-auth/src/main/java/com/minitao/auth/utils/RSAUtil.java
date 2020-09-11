//package com.minitao.auth.utils;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.util.Base64Utils;
//
//import javax.crypto.Cipher;
//import java.io.ByteArrayOutputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.security.*;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//import java.security.spec.InvalidKeySpecException;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.Base64;
//import java.util.Properties;
//
///**
// * 实现RSA加解密
// * @author : lijialun
// * @description:
// */
//public class RSAUtil {
//    private static final Logger LOGGER = LoggerFactory.getLogger(RSAUtil.class);
//    /** 算法名称 */
//    private static final String ALGORITHM =  "RSA";
//    /** RSA签名算法 */
//    private static final String RSA_SIGNATURE_ALGORITHM = "SHA256WithRSA";
//    /** 默认密钥大小 */
//    private static final int KEY_SIZE = 2048;
//    /** 最大解密长度 */
//    private static final int MAX_DECRYPT_BLOCK = 256;
//    /** 用来指定保存密钥对的文件名和存储的名称 */
//    private static final String PUBLIC_KEY_NAME = "publicKey";
//    private static final String PRIVATE_KEY_NAME = "privateKey";
//    private static final String PUBLIC_FILENAME = "publicKey.properties";
//    private static final String PRIVATE_FILENAME = "privateKey.properties";
//    private static Properties pubProperties;
//    private static Properties PriProperties;
//    /** 密钥对生成器 */
//    private static KeyPairGenerator keyPairGenerator = null;
//
//    private static KeyFactory keyFactory = null;
//    /** 缓存的密钥对 */
//    private static KeyPair keyPair = null;
//    /** Base64 编码/解码器 JDK1.8 */
//    private static Base64.Decoder decoder = Base64.getDecoder();
//    private static Base64.Encoder encoder = Base64.getEncoder();
//
//    /** 初始化密钥工厂 */
//    static{
//        try {
//            keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
//            keyFactory = KeyFactory.getInstance(ALGORITHM);
//            //generateKeyPair();
//            getInstanceForPub();
//            getInstanceForPri();
//        } catch (NoSuchAlgorithmException e) {
//            LOGGER.error(e.getMessage(),e);
//        }
//    }
//    /*初始化公钥config*/
//    private static Properties getInstanceForPub(){
//        if (pubProperties == null) {
//            Resource res  =new ClassPathResource(PUBLIC_FILENAME);
//            pubProperties = new Properties();
//            try {
//                pubProperties.load(res.getInputStream());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return pubProperties;
//    }
//    /*初始钥私钥config*/
//    private static Properties getInstanceForPri(){
//        if (PriProperties == null) {
//            Resource res  =new ClassPathResource(PRIVATE_FILENAME);
//            PriProperties = new Properties();
//            try {
//                PriProperties.load(res.getInputStream());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return PriProperties;
//    }
//    /** 私有构造器 */
//    private RSAUtil(){}
//
//    /**
//     * 生成密钥对
//     * 将密钥分别用Base64编码保存到#publicKey.properties#和#privateKey.properties#文件中
//     * 保存的默认名称分别为publicKey和privateKey
//     *
//     *
//     */
//    public static synchronized void generateKeyPair(){
//        try {
//            keyPairGenerator.initialize(KEY_SIZE,new SecureRandom());
//            keyPair = keyPairGenerator.generateKeyPair();
//        } catch (InvalidParameterException e){
//            LOGGER.error("KeyPairGenerator does not support a key length of " + KEY_SIZE + ".",e);
//        } catch (NullPointerException e){
//            LOGGER.error("RSAUtils#key_pair_gen is null,can not generate KeyPairGenerator instance.",e);
//        }
//        RSAPublicKey rsaPublicKey = (RSAPublicKey)keyPair.getPublic();
//        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)keyPair.getPrivate();
//        String publicKeyString = encoder.encodeToString(rsaPublicKey.getEncoded());
//        String privateKeyString = encoder.encodeToString(rsaPrivateKey.getEncoded());
//        System.out.println("公钥:"+publicKeyString);
//        System.out.println("私钥:"+privateKeyString);
//        storeKey(publicKeyString,PUBLIC_KEY_NAME,pubProperties,PUBLIC_FILENAME);
//        storeKey(privateKeyString,PRIVATE_KEY_NAME,PriProperties,PRIVATE_FILENAME);
//    }
//
//    /**
//     * 将指定的密钥字符串保存到文件中,如果找不到文件，就创建
//     * @param keyString 密钥的Base64编码字符串（值）
//     * @param keyName  保存在文件中的名称（键）
//     * @param properties 目标文件
//     */
//    private static void storeKey(String keyString,String keyName,Properties properties,String fileName){
//        try {
//            Resource res  = new ClassPathResource(fileName);
//            FileOutputStream oFile = new FileOutputStream(res.getFile(), false);
//            properties.setProperty(keyName,keyString);
//            properties.store(oFile, keyName);
//            oFile.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    /**
//     * 获取密钥字符串
//     * @param keyName 需要获取的密钥名
//     * @param properties 密钥文件
//     * @return Base64编码的密钥字符串
//     */
//    private static String getKeyString(String keyName,Properties properties){
//        return properties.getProperty(keyName);
//    }
//
//    /**
//     * 从文件获取RSA公钥
//     * @return RSA公钥
//     * @throws InvalidKeySpecException
//     */
//    public static RSAPublicKey getPublicKey(){
//        try {
//            byte[] keyBytes = decoder.decode(getKeyString(PUBLIC_KEY_NAME,pubProperties));
//            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
//            RSAPublicKey rsa = (RSAPublicKey)keyFactory.generatePublic(x509EncodedKeySpec);
//            return rsa;
//        }catch (InvalidKeySpecException e) {
//            LOGGER.error("getPublicKey()#" + e.getMessage(),e);
//        }
//        return null;
//    }
//
//    /**
//     * 从文件获取RSA私钥
//     * @return RSA私钥
//     * @throws InvalidKeySpecException
//     */
//    public static RSAPrivateKey getPrivateKey(){
//        try {
//            byte[] keyBytes = decoder.decode(getKeyString(PRIVATE_KEY_NAME,PriProperties));
//            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
//            return (RSAPrivateKey)keyFactory.generatePrivate(pkcs8EncodedKeySpec);
//        } catch (InvalidKeySpecException e) {
//            LOGGER.error("getPrivateKey()#" + e.getMessage(),e);
//        }
//        return null;
//    }
//    /**
//     * RSA公钥加密
//     */
//    public static byte[] encryptByPublicKey(byte[] data) throws Exception {
//        // 对数据加密
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey());
//        int inputLen = data.length;
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        int offSet = 0;
//        byte[] cache;
//        int i = 0;
//        // 对数据分段加密
//        while (inputLen - offSet > 0) {
//            if (inputLen - offSet > 117) {
//                cache = cipher.doFinal(data, offSet, 117);
//            } else {
//                cache = cipher.doFinal(data, offSet, inputLen - offSet);
//            }
//            out.write(cache, 0, cache.length);
//            i++;
//            offSet = i * 117;
//        }
//        byte[] encryptedData = out.toByteArray();
//        out.close();
//        return encryptedData;
//    }
//
//    //私钥解密
//    public static byte[] decryptByPrivateKey(byte[] encryptedData) throws Exception {
//        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(getPrivateKey().getEncoded());
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
//        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
//        cipher.init(Cipher.DECRYPT_MODE, privateK);
//        int inputLen = encryptedData.length;
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        int offSet = 0;
//        byte[] cache;
//        int i = 0;
//        // 对数据分段解密
//        while (inputLen - offSet > 0) {
//            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
//                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
//            } else {
//                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
//            }
//            out.write(cache, 0, cache.length);
//            i++;
//            offSet = i * MAX_DECRYPT_BLOCK;
//        }
//        byte[] decryptedData = out.toByteArray();
//        out.close();
//        return decryptedData;
//    }
//
//    /**
//     * RSA公钥验签
//     *
//     * @param data
//     *            待签名字符串
//     *            公钥（Base64编码）
//     * @return 验签结果
//     * @throws Exception
//     */
//    public static boolean verify(byte[] data, String sign) throws Exception {
//        Signature signature = Signature.getInstance(RSA_SIGNATURE_ALGORITHM);
//        signature.initVerify(getPublicKey());
//        signature.update(data);
//        return signature.verify(Base64Utils.decodeFromUrlSafeString(sign));
//    }
//
//    /**
//     * RSA私钥签名：签名方式SHA1withRSA
//     *
//     * @param data
//     *            待签名byte[]
//     *            私钥（Base64编码）
//     * @return 签名byte[]
//     * @throws Exception
//     */
//    public static byte[] sign(byte[] data) throws Exception {
//        // Sign
//        Signature signature = Signature.getInstance(RSA_SIGNATURE_ALGORITHM);
//        signature.initSign(getPrivateKey());
//        signature.update(data);
//        return signature.sign();
//    }
//
//
//
//    /**
//     * 将char转换为byte
//     * @param c char
//     * @return byte
//     */
//    private static byte toByte(char c){
//        return (byte)"0123456789ABCDEF".indexOf(c);
//    }
//
//
//    public static int getValidLength(byte[] bytes){
//        int i = 0;
//        if (null == bytes || 0 == bytes.length)
//            return i ;
//        for (; i < bytes.length; i++) {
//            if (bytes[i] == '\0')
//                break;
//        }
//        return i + 1;
//    }
//
//    public static KeyPair GetKeyPair(){
//        return new KeyPair(getPublicKey(),getPrivateKey());
//    }
//
//}