package com.cjwx.spark.engine.util;

import com.cjwx.spark.engine.core.constant.AppConstant;
import com.cjwx.spark.engine.core.exception.ServiceException;
import org.springframework.util.Base64Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @Description: 加密解密工具类
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
public class EndecryptUtils {

    public static final char[] DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final String KEY_SHA = "SHA";
    public static final String KEY_MD5 = "MD5";

    /**
     * base64进制加密
     */
    public static String encrytBase64(String password) {
        return Base64Utils.encodeToString(password.getBytes());
    }

    /**
     * base64进制解密
     */
    public static String decryptBase64(String cipherText) {
        return new String(Base64Utils.decodeFromString(cipherText));
    }

    /**
     * 16进制加密
     */
    public static String encrytHex(String password) {
        return StringUtils.isNotEmpty(password) ? encode(password.getBytes()) : StringUtils.NULL_STRING;
    }

    /**
     * 16进制解密
     */
    public static String decryptHex(String cipherText) {
        return StringUtils.isNotEmpty(cipherText) ? decode(cipherText.toCharArray()) : StringUtils.NULL_STRING;
    }

    /**
     * md5加密 迭代1次
     */
    public static String md5Password(String password, String salt) {
        return encrytPassword(password, salt, KEY_MD5, 1);
    }

    /**
     * sha加密 迭代1次
     */
    public static String shaPassword(String password, String salt) {
        return encrytPassword(password, salt, KEY_SHA, 1);
    }

    /**
     * 密码加密
     */
    public static String encrytPassword(String password, String salt, String algorithm, int hashIterations) {
        MessageDigest digest = getMessageDigest(algorithm, salt);
        byte[] hashed = digest.digest(StringUtils.stringToByte(password));
        if (hashIterations > 1) {
            for (int i = 0; i < hashIterations - 1; ++i) {
                digest.reset();
                hashed = digest.digest(hashed);
            }
        }
        return encode(hashed);
    }

    /**
     * 获取数据加密类 洒上盐
     */
    public static MessageDigest getMessageDigest(String algorithm, String salt) {
        MessageDigest digest = getMessageDigest(algorithm);
        digest.reset();
        digest.update(StringUtils.stringToByte(salt));
        return digest;
    }

    /**
     * 获取数据加密类
     */
    public static MessageDigest getMessageDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("数据加密类 [" + algorithm + "] 获取异常", e);
        }
    }

    /**
     * 随机生成salt
     */
    public static String createSalt() {
        byte[] bytes = new byte[16];
        new SecureRandom().nextBytes(bytes);
        return encode(bytes);
    }

    /**
     * 生成signingKey
     */
    public static String createSigningKey() {
        return encrytBase64(AppConstant.SIGNING_KEY);
    }

    public static String encode(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        int i = 0;

        for (int var4 = 0; i < l; ++i) {
            out[var4++] = DIGITS[(240 & data[i]) >>> 4];
            out[var4++] = DIGITS[15 & data[i]];
        }

        return new String(out);
    }

    public static String decode(char[] data) throws IllegalArgumentException {
        int len = data.length;
        if ((len & 1) != 0) {
            throw new IllegalArgumentException("Odd number of characters.");
        } else {
            byte[] out = new byte[len >> 1];
            int i = 0;
            for (int j = 0; j < len; ++i) {
                int f = Character.digit(data[j], 16) << 4;
                ++j;
                f |= Character.digit(data[j], 16);
                ++j;
                out[i] = (byte) (f & 255);
            }
            return new String(out);
        }
    }

}
