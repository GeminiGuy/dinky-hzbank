package com.dlink.utils;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.dlink.configure.RSAProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * RSA
 */
@Component
public class RSAUtils {

    @Resource
    private RSAProperties rsaProperties;

    private static RSAUtils rsaUtils;

    @PostConstruct
    public void initRSAUtils() {
        rsaUtils = this;
    }

    /**
     * 使用私钥解密被公钥加密的字符串
     *
     * @param encryptDataByPublicKey 被公钥加密的字符串
     * @return 解密后的字符串
     */
    public static String decryptStr(String encryptDataByPublicKey) {
        RSA privateRSA = new RSA(rsaUtils.rsaProperties.getPrivateKey(), null);

        return privateRSA.decryptStr(encryptDataByPublicKey, KeyType.PrivateKey);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 使用工具生成默认的公私钥键值对
        RSA rsa = new RSA();
        String publicKeyStr = rsa.getPublicKeyBase64();
        String privateKeyStr = rsa.getPrivateKeyBase64();
        // 输出生成的公私钥键值对
        System.out.println("publicKeyStr:" + publicKeyStr);
        System.out.println("privateKeyStr:" + privateKeyStr);

        // 测试加密与解密
        String currentPublicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCygU6+dtVnyFFL3+4Hn3WKyRRMRqm2UG1Ww+KYdGVBUqucoluXrKT678XgJtIsWzcfjzHV3/FskfIKaXkZlSwAQ0hQRaiM11tU5HEJN63g0aYsIS95/iZq6xxLrf89g260x14W6MunLhgU0gS9WPVqZlcdikefyslaUJ16QOKx9wIDAQAB";
        String currentPrivateKeyStr = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALKBTr521WfIUUvf7gefdYrJFExGqbZQbVbD4ph0ZUFSq5yiW5espPrvxeAm0ixbNx+PMdXf8WyR8gppeRmVLABDSFBFqIzXW1TkcQk3reDRpiwhL3n+JmrrHEut/z2DbrTHXhboy6cuGBTSBL1Y9WpmVx2KR5/KyVpQnXpA4rH3AgMBAAECgYAHiAjxMUtZiLkHxgFuS4FZyZhN8l2yH8+VLXucg61+ZZIKPRfQ0CU/1Y9lBzSvHJ24ett9hhufp8K6D+9eyUC+o6fC1MCq7W8+hsPl5jKDp4uqViRuLl1JFRif/HTrZPupsgCmuvZPnHl0DjQTbXq3Bay9Y/wZ8J0xWQp0L1cK6QJBANY/8y8Cc10UelghEhCKKc9M3KHQi+sMf1rjlWrlKFOUlj4iWisByqjlz5zrC9GMCNjKMGJz2gWdHujPFRzAMJ8CQQDVSjNJQ4m5eeqltvngEAlKfyHVylnxad7Aa1jUWqeDbiRrEyx/GzdV+4hjqp6LXse8u+yr/7NfZM2zieGxacepAkBc2M/ZUnWOI961LHe4pTdjN8rxcQABt6PFTpUlAvQL7HuDp71tn9Qkh5sGRLIVeFspH84CDHSyNBn+CCf9Eqi/AkAdUDBSPlz8R7wvAEHBQJL2URimWEQaQLGjgl1X+gmRr56DtMPlyCjHwWUSRY26m9EZqD4zuVW+o4z9wtJo3p/ZAkBqQ+h9E8FZvg6Jm9gpvACAB3xffAg4XlgEEgGGdYwBllZ+Z5oORLx1EFF8rNsqWd8pQFaNHx93ZvffO0+xzm1o";
        // 需要加密的字符串
        String data = "snowdream";
        RSA publicRSA = new RSA(null, currentPublicKeyStr);
        String encryptStr = publicRSA.encryptBase64(data, KeyType.PublicKey);
        RSA privateRSA = new RSA(currentPrivateKeyStr, null);
        String decryptStr = privateRSA.decryptStr(encryptStr, KeyType.PrivateKey);
        // 输出测试加解密结果
        System.out.println("加密后：" + encryptStr);
        System.out.println("解密后：" + decryptStr);
        // 需要加密的字符串
        String data2 = "applecandy";
        RSA publicRSA2 = new RSA(null, currentPublicKeyStr);
        String encryptStr2 = publicRSA2.encryptBase64(data2, KeyType.PublicKey);
        RSA privateRSA2 = new RSA(currentPrivateKeyStr, null);
        String decryptStr2 = privateRSA2.decryptStr(encryptStr2, KeyType.PrivateKey);
        // 输出测试加解密结果
        System.out.println("字符串2加密后：" + encryptStr2);
        System.out.println("字符串2解密后：" + decryptStr2);

    }

}
