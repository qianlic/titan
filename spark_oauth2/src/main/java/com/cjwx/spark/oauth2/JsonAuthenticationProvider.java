package com.cjwx.spark.oauth2;

import com.cjwx.spark.engine.util.EndecryptUtils;
import com.cjwx.spark.oauth2.entity.OAuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年09月05日} 22:55
 */
@Slf4j
public class JsonAuthenticationProvider implements AuthenticationProvider {

    private UserCache userCache = new NullUserCache();

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() == null ? "NONE_PROVIDED" : authentication.getName();
        boolean cacheWasUsed = true;
        UserDetails loadedUser = this.userCache.getUserFromCache(username);
        if (loadedUser == null) {
            cacheWasUsed = false;
            try {
                loadedUser = userDetailsService.loadUserByUsername(username);
            } catch (UsernameNotFoundException ex) {
                log.debug("User '" + username + "' not found");
                throw ex;
            }
        }

        OAuthUser user = (OAuthUser) loadedUser;

        String password = EndecryptUtils.md5Password(authentication.getCredentials().toString(), user.getSalt());

        if (!password.equalsIgnoreCase(user.getPassword())) {
            log.debug("Authentication failed: password does not match stored value");
            throw new BadCredentialsException("认证失败,用户名或密码错误");
        }
        if (!cacheWasUsed) {
            this.userCache.putUserInCache(user);
        }
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(username, username, authentication.getAuthorities());
        result.setDetails(authentication.getDetails());
        return result;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
