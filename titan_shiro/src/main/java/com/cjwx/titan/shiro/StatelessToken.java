package com.cjwx.titan.shiro;

import com.cjwx.titan.engine.reids.jwt.JwtToken;
import com.cjwx.titan.engine.util.ObjectUtils;
import com.cjwx.titan.engine.util.StringUtils;
import com.cjwx.titan.shiro.bean.SysResourceBean;
import com.cjwx.titan.shiro.bean.SysRoleBean;
import com.cjwx.titan.shiro.bean.SysUserBean;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description: 无状态Token
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Data
public class StatelessToken extends JwtToken implements AuthenticationToken {

    public static final String TYPE_LOGIN = "LOGIN";
    public static final String TYPE_AUTHC = "AUTHC";

    private Object principal;
    private Object credentials;
    private String type;
    private List<SysRoleBean> roles;
    private List<SysResourceBean> resources;

    public StatelessToken(String username, String host) {
        this(username, null, StatelessToken.TYPE_AUTHC, host);
    }

    public StatelessToken(String username, String clientDigest, String host) {
        this(username, clientDigest, StatelessToken.TYPE_LOGIN, host);
    }

    public StatelessToken(String username, String clientDigest, String type, String host) {
        this.principal = username;
        this.credentials = clientDigest;
        this.setHost(host);
        if (StatelessToken.TYPE_LOGIN.equals(type)) {
            this.setTokenId(StringUtils.randomUUID());
            this.type = StatelessToken.TYPE_LOGIN;
        } else {
            this.type = StatelessToken.TYPE_AUTHC;
        }
    }

    public void setUser(SysUserBean user) {
        this.put("userid", user.getId());
        this.put("usercode", user.getUsercode());
        this.put("username", user.getUsername());
        this.put("sex", user.getSex());
        this.put("email", user.getEmail());
        this.setRoles(user.getRoles());
        this.setResources(user.getResources());
    }

    public void setRoles(List<SysRoleBean> roles) {
        this.roles = roles;
    }

    public void setResources(List<SysResourceBean> resources) {
        this.resources = resources;
        this.put("resources", resources);
    }

    public Set<String> getRoles() {
        if (ObjectUtils.isEmpty(this.roles)) {
            return null;
        }
        return this.roles.stream()
                .map(SysRoleBean::getRolecode)
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toSet());
    }

    public Set<String> getPermissions() {
        if (ObjectUtils.isEmpty(this.resources)) {
            return null;
        }
        return this.resources.stream()
                .filter(r -> "3".equals(r.getType()))
                .map(SysResourceBean::getUrl)
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toSet());
    }

}
