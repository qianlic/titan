package com.cjwx.titan.shiro.realm.credentials;

import com.cjwx.titan.shiro.StatelessAuthenticationInfo;
import com.cjwx.titan.shiro.StatelessToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 限制登录次数，如果5次出错，锁定1个小时
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
public class CredentialsMatcher extends HashedCredentialsMatcher {

    private static final String PASSWORD_RETRY_CACHE = "passwordRetryCache";
    private static final int RETRY_LIMIT = 5;
    private Cache<String, AtomicInteger> passwordRetryCache;

    public CredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache(PASSWORD_RETRY_CACHE);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        if (info.getCredentials() instanceof Boolean) {
            return (Boolean) info.getCredentials();
        }
        return doCredentialsMatch((StatelessToken) token, (StatelessAuthenticationInfo) info);
    }

    public boolean doCredentialsMatch(StatelessToken token,StatelessAuthenticationInfo info){
        String username = (String) token.getPrincipal();

        AtomicInteger retryCount = passwordRetryCache.get(username);
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
        }
        if (retryCount.incrementAndGet() > RETRY_LIMIT) {
            throw new ExcessiveAttemptsException();
        }
        passwordRetryCache.put(username, retryCount);
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            passwordRetryCache.remove(username);
        }
        token.setUser(info.getSysUserBean());
        return matches;
    }

}
