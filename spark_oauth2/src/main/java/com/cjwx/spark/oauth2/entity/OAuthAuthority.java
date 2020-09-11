package com.cjwx.spark.oauth2.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年09月03日} 15:12
 */
@Data
public class OAuthAuthority implements GrantedAuthority {

    private String authority;

    public OAuthAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof OAuthAuthority) {
            OAuthAuthority OAuthAuthority = (OAuthAuthority) obj;
            return this.authority.equals(OAuthAuthority.getAuthority());
        }
        if (obj instanceof String) {
            return this.authority.equals(obj.toString());
        }
        return false;
    }

}
