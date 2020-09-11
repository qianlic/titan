package com.cjwx.spark.oauth2.entity;

import com.cjwx.spark.engine.reids.jwt.JwtToken;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年09月03日} 10:41
 */
@Data
public class OAuthUser extends JwtToken implements UserDetails {

    private String userCode;

    private String username;

    private String password;

    private String salt;

    private Boolean status;

    private List<OAuthAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getStatus();
    }

}
