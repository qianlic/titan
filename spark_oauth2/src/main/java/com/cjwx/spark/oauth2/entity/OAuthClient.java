package com.cjwx.spark.oauth2.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年09月03日} 10:41
 */
@Data
public class OAuthClient implements ClientDetails {

    private String clientId;

    private String clientSecret;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    /**
     * 跳过授权
     */
    private boolean isAutoApprove;

    private Set<String> scope;

    private Set<String> autoApproveScopes;

    private Set<String> authorizedGrantTypes;

    private Set<String> resourceIds;

    private Set<String> registeredRedirectUri;

    private List<GrantedAuthority> authorities = Collections.emptyList();;

    /**
     * 自定义属性
     */
    private Map<String, Object> AdditionalInformation;

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = StringUtils.commaDelimitedListToSet(authorizedGrantTypes);
    }

    public void setScope(String scopes) {
        this.scope = StringUtils.commaDelimitedListToSet(scopes);
    }

    @Override
    public boolean isSecretRequired() {
        return this.clientSecret != null;
    }

    @Override
    public boolean isScoped() {
        return this.scope != null && !this.scope.isEmpty();
    }

    @Override
    public boolean isAutoApprove(String scope) {
        if (autoApproveScopes == null) {
            return false;
        }
        for (String auto : autoApproveScopes) {
            if (auto.equals("true") || scope.matches(auto)) {
                return true;
            }
        }
        return false;
    }

}
