package cn.akira.util;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSATest {
    public static void main(String[] args) throws Exception {
        //生成公钥和私钥
        Map<String,String> keyMap = genKeyPair();
        //加密字符串
        String message = "123";
        System.out.println("随机生成的公钥为:" + keyMap.get("pubKey"));
        System.out.println("随机生成的私钥为:" + keyMap.get("prvKey"));
        String messageEn = encrypt(message,keyMap.get("pubKey"));
        System.out.println(message + "\t加密后的字符串为:" + messageEn);
        String messageDe = decrypt(messageEn,keyMap.get("prvKey"));
        System.out.println("还原后的字符串为:" + messageDe);
    }

    @Test
    public void enc(){
        String originStr = "莫新店";

//        String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCupVYROPOAv3qvL33jOd4hkVUTfwvjFNPbKChHkClYiZ7oAnBc5y/ouSsqk2bBJA939EJYXKFRjOJ4s8/GviwYbRzpJcgGIPhyq8PS1TTzgp0yq3H1yxpxgxc5Dl5oqeEJ945qozrhSlPQaCRfOyTWFAJbs/DHiFMxgM5S+0ih4wIDAQAB";
        try {
            String pubKey = genKeyPair().get("pubKey");
            System.out.println(encrypt(originStr,pubKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deEnc(){
        String encStr = "JvwmcAyBt4shgZs8J/MLASF8gksXZ5rygjw/33XumGvhdHEx0Y1aPbweMY9fAm2AZy7Rl+iFwTcndxtthvcNpTcFuT6muJ9ZOE09HptdQ2Oj9lHgeUSPyJB5DhAeDXEuKPHKpMI5JVkKmHrXzsF7ayvcwCCqCnAWGS37NG2tJs0=";
        String prvKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAK6lVhE484C/eq8vfeM53iGRVRN/C+MU09soKEeQKViJnugCcFznL+i5KyqTZsEkD3f0QlhcoVGM4nizz8a+LBhtHOklyAYg+HKrw9LVNPOCnTKrcfXLGnGDFzkOXmip4Qn3jmqjOuFKU9BoJF87JNYUAluz8MeIUzGAzlL7SKHjAgMBAAECgYBk0PzGJGIsyFxCFOBO83DPX94EyypJhBRoRt45sXasrYUV2ZKTjsGRTpMkmMKQbAih7lb+OzrEoNd7ZhFjYRSCbnSwyZ5qefBqENneNb+v7VkBaXw2AEl3Uz8KoHdsuAZhYKSIjjVCZsfY1p3og0LjYjCLIZP1y0wLiPY2WL6RAQJBAPT6NfPdunIhY+UjVByYReiCfz4HEMIh1cU3I5SRZ7sLS6TkY9O8WruGooPFUnsvOqwTCirhl7u9z+FAcf9pXrMCQQC2gQGzaMd8NIUolT3RJiPnrA8q9P6ActiAm0s79z9MkvyVbPSMfbWgitBAIxM87pQr+1q5TIZdU1h+rtnmPUgRAkAsZHsKag+zRuG3UCOZ2u4wKBWuw9EBnOgECO3lqkPF3VIj209Lu0Il4Uvp3QQJVpGnRPv+sUqxtr/I7ci6o/mJAkAV0Bt87wQQw1I7BINr8QwPj5AMiVW10oQv8t+uHiIRq+vGjEw6UngX5R942vhcqwNIsnUoeo4Ar4p/MbTXgPGRAkAeZQqMb/Cz7Bv+7SmkowSKWv2oOuhTw8tL9rGjZhauBL5iAyQ9KE4CfpCbExreYbYFfr5soquQno2vnD2fLp7h";
        try {
            System.out.println("解密字符:"+decrypt(encStr,prvKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 随机生成密钥对
     * @throws NoSuchAlgorithmException escape error
     */
    private static Map<String,String> genKeyPair() throws NoSuchAlgorithmException {

        Map<String,String> keyMap = new HashMap<>();

        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        keyMap.put("pubKey",publicKeyString);  //公钥
        keyMap.put("prvKey",privateKeyString);  //1私钥
        return keyMap;
    }
    /**
     * RSA公钥加密
     * @param str 加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    private static String encrypt( String str, String publicKey ) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
    }
    /**
     * RSA私钥解密
     *
     * @param str 加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    private static String decrypt(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }
}