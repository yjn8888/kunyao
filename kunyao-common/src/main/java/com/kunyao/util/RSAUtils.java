package com.kunyao.util;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


public final class RSAUtils {

    private static final String CHARSET = "UTF-8";
    private static final String RSA_ALGORITHM = "RSA";


    public static Map<String, String> createKeys(){
        //为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try{
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        }catch(NoSuchAlgorithmException e){
            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
        }

        //初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(1024);
        //生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded());
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<String, String>();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

        return keyPairMap;
    }

    /**
     * 得到公钥
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    private static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 得到私钥
     * @param privateKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    private static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     */
    private static String publicEncrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥解密
     * @param data
     * @param privateKey
     * @return
     */

    public static String privateDecrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), privateKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥加密
     * @param data
     * @param privateKey
     * @return
     */

    private static String privateEncrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 公钥解密
     * @param data
     * @param publicKey
     * @return
     */

    private static String publicDecrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), publicKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize){
        int maxBlock = 0;
        if(opmode == Cipher.DECRYPT_MODE){
            maxBlock = keySize / 8;
        }else{
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try{
            while(datas.length > offSet){
                if(datas.length-offSet > maxBlock){
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                }else{
                    buff = cipher.doFinal(datas, offSet, datas.length-offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        }catch(Exception e){
            throw new RuntimeException("加解密阀值为["+maxBlock+"]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }

    /**
         * 利用java原生的类实现SHA256加密
         * @param str 加密后的报文
         * @return
         */
    private static String getSHA256(String str){
        MessageDigest messageDigest;
        String encodestr = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;
     }
    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
     private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
            //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
     }

    /**
     * @param srcStr 明文
     * @param publicKey 公钥
     * @param clientId 客户ID
     * @return 公钥签名
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
     public static String getPublicKeySignature(String srcStr,  String publicKey,String clientId) throws InvalidKeySpecException, NoSuchAlgorithmException {
         srcStr = getSHA256(srcStr) + clientId;
         return publicEncrypt(srcStr,getPublicKey(publicKey));
     }

    /**
     * @param srcStr  明文
     * @param publicKeySignature 公钥签名
     * @param privateKey  私钥
     * @param clientId  客户ID
     * @return 验签结果
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    public static boolean verifyPublicKeySignature(String srcStr, String publicKeySignature, String privateKey,String clientId) throws InvalidKeySpecException, NoSuchAlgorithmException {
         String target = privateDecrypt(publicKeySignature,getPrivateKey(privateKey));
         srcStr = getSHA256(srcStr)+ clientId;
        return srcStr.equals(target);
    }

    /**
     * @param srcStr 明文
     * @param privateKey 私钥
     * @param clientId 客户端标示
     * @return 私钥签名
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    public static String getPrivateKeySignature(String srcStr, String privateKey, String clientId) throws InvalidKeySpecException, NoSuchAlgorithmException {
        srcStr = getSHA256(srcStr) + clientId;
        return privateEncrypt(srcStr,getPrivateKey(privateKey));
    }

    /**
     * @param srcStr 明文
     * @param privateKeySignature  私钥签名
     * @param publicKey 公钥
     * @param clientId 客户ID
     * @return 校验私钥签名的结果
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    public static boolean verifyPrivateKeySignature(String srcStr, String privateKeySignature, String publicKey,String clientId) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String target = publicDecrypt(privateKeySignature,getPublicKey(publicKey));
        srcStr = getSHA256(srcStr)+ clientId;
        return srcStr.equals(target);
    }

    /**
     * @param srcStr 明文
     * @param publicKey 公钥
     * @return 密文
     */
    public static String publicKeyEncrypt(String srcStr, String publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return publicEncrypt(srcStr,getPublicKey(publicKey));
    }

    /**
     * @param srcStr 密文
     * @param privateKey 私钥
     * @return 明文
     */
    public static String privateKeyDecrypt(String srcStr, String privateKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return privateDecrypt(srcStr,getPrivateKey(privateKey));
    }

    /**
     * @param srcStr 明文
     * @param privateKey 私钥
     * @return 密文
     */
    public static String privateKeyEncrypt(String srcStr, String privateKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return privateEncrypt(srcStr,getPrivateKey(privateKey));
    }

    /**
     * @param srcStr 密文
     * @param publicKey 公钥
     * @return 明文
     */
    public static String publicKeyDecrypt(String srcStr, String publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return publicDecrypt(srcStr,getPublicKey(publicKey));
    }

}
