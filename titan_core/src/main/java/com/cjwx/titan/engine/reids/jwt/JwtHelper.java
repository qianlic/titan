package com.cjwx.titan.engine.reids.jwt;

import com.cjwx.titan.engine.core.constant.HttpConstant;
import com.cjwx.titan.engine.core.exception.ServiceException;
import com.cjwx.titan.engine.reids.util.RedisUtils;
import com.cjwx.titan.engine.util.EndecryptUtils;
import com.cjwx.titan.engine.util.StringUtils;
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
public class JwtHelper {

    public static final String AUTHORIZATION_KEY = "Authorization";
    public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    public static final String SIGNING_TOKEN_KEY = EndecryptUtils.createSigningKey();
    public static final long EXPIRE_TIME = 5 * 60 * 60;

    /**
     * 生成JWT TOKEN
     */
    public static String createJWT(JwtToken token) {
        String tokenId = token.getTokenId();
        JwtBuilder jwtBuilder = createJWT(tokenId, token.getHost());
        RedisUtils.set(AUTHORIZATION_KEY + "." + HttpConstant.VERSION + "." + tokenId, token, EXPIRE_TIME);
        return jwtBuilder.compact();
    }

    /**
     * 生成JWT
     */
    public static JwtBuilder createJWT(String tokenId, String host) {
        long time = System.currentTimeMillis();
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setId(tokenId)
                .setIssuer(StringUtils.trim(host))
                .setIssuedAt(new Date(time))
                .setExpiration(new Date(time + 1000 * EXPIRE_TIME))
                .signWith(SIGNATURE_ALGORITHM, generalKey());
    }


    /**
     * 删除Token
     */
    public static void removeToken(String authorization, String host) {
        try {
            String tokenKey = parseTokenKey(authorization, host);
            RedisUtils.remove(tokenKey);
        } catch (Exception e) {
            log.debug("删除Token", e);
        }
    }

    /**
     * 获取token
     */
    public static JwtToken parseToken(String authorization, String host) {
        String tokenKey = parseTokenKey(authorization, host);
        if (RedisUtils.exists(tokenKey)) {
            RedisUtils.expire(tokenKey, EXPIRE_TIME);
            return (JwtToken) RedisUtils.get(tokenKey);
        }
        return null;
    }

    /**
     * 解析JWT获取TokenKey
     */
    public static String parseTokenKey(String authorization, String host) {
        return AUTHORIZATION_KEY + "." + HttpConstant.VERSION + "." + parseTokenId(authorization, host);
    }

    /**
     * 解析JWT获取TokenId
     */
    public static String parseTokenId(String authorization, String host) {
        Claims claims = parseJWT(authorization);
        if (!claims.getIssuer().equalsIgnoreCase(StringUtils.trim(host))) {
            throw new ServiceException("用户登录IP地址异常，请重新认证！");
        } else if (claims.getExpiration().getTime() < System.currentTimeMillis()) {
            throw new ServiceException("凭据已过期，请重新认证！");
        }
        return claims.getId();
    }

    /**
     * 解析JWT
     */
    public static Claims parseJWT(String token) {
        return Jwts.parser()
                .setSigningKey(generalKey())
                .parseClaimsJws(token).getBody();
    }

    /**
     * 私钥
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(SIGNING_TOKEN_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

}
