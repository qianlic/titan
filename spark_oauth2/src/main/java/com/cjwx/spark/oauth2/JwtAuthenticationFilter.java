package com.cjwx.spark.oauth2;

import com.cjwx.spark.engine.util.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年09月04日} 16:04
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    protected String getJwtToken(HttpServletRequest request) {
        String authInfo = request.getHeader("Authorization");
        return StringUtils.removeStart(authInfo, "Bearer ");
    }


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            String token = getJwtToken(request);
            if (StringUtils.isNotBlank(token)) {
                Claims claims = JwtTokenUtils.parseJWT(token);
                String tokenId = claims.getId();

                if (tokenId != null) {
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(tokenId, null, null));
                }
            }
        } catch (AuthenticationException e) {
        }
        filterChain.doFilter(request, response);
    }

}
