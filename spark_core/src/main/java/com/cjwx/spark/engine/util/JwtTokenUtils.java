package com.cjwx.spark.engine.util;

import com.alibaba.fastjson.JSON;
import com.cjwx.spark.engine.core.constant.AppConstant;
import com.cjwx.spark.engine.core.dto.TokenDTO;
import com.cjwx.spark.engine.web.http.RequestHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * @Description: JWT 助手
 * @Author: qian li
 * @Date: 2018年03月31日 16:20
 */
@Slf4j
public class JwtTokenUtils {

    public static final String AUTHORIZATION_KEY = "Authorization";
    public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    public static final String SIGNING_TOKEN_KEY = EndecryptUtils.createSigningKey();
    public static final long EXPIRE_TIME = 5 * 60 * 60;

    /**
     * 生成JWT TOKEN
     */
    public static String createJWT(TokenDTO subject) {
        return createJWT().setId(StringUtils.randomUUID())
                .setIssuer(RequestHelper.getClientIp())
                .setSubject(JSON.toJSONString(subject))
                .compact();
    }

    /**
     * 生成JWT
     */
    public static JwtBuilder createJWT() {
        long time = System.currentTimeMillis();
        return Jwts.builder().setHeaderParam("type", "JWT")
                .setIssuedAt(new Date(time))
                .setExpiration(new Date(time + 1000 * EXPIRE_TIME))
                .signWith(SIGNATURE_ALGORITHM, generalKey());
    }


    /**
     * 解析JWT获取TokenId
     */
    public static TokenDTO parseToken(String token) {
        Claims claims = parseJWT(token);
        String tokenId = claims.getId();
        if (RedisUtils.exists(AUTHORIZATION_KEY + ":" + AppConstant.VERSION + ":" + tokenId)) {
            ExceptionUtils.throwError("TOKEN已失效，请重新认证！");
        } else if (!claims.getIssuer().equalsIgnoreCase(RequestHelper.getClientIp())) {
            ExceptionUtils.throwError("用户登录IP地址异常，请重新认证！");
        } else if (claims.getExpiration().getTime() < System.currentTimeMillis()) {
            ExceptionUtils.throwError("凭据已过期，请重新认证！");
        }
        return JSON.parseObject(claims.getSubject(), TokenDTO.class);
    }

    /**
     * 解析JWT
     */
    public static Claims parseJWT(String token) {
        return Jwts.parser().setSigningKey(generalKey())
                .parseClaimsJws(token).getBody();
    }

    /**
     * 私钥
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(SIGNING_TOKEN_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    public static void addBlackList(String token) {
        try {
            Claims claims = parseJWT(token);
            String tokenId = claims.getId();
            long expirationTime = claims.getExpiration().getTime();
            long currentTimeMillis = System.currentTimeMillis();
            if (expirationTime > currentTimeMillis) {
                long expireTime = expirationTime - currentTimeMillis;
                RedisUtils.set(AUTHORIZATION_KEY + ":" + AppConstant.VERSION + ":" + tokenId, token, expireTime);
            }
        } catch (Exception ignored) {
        }
    }

}
