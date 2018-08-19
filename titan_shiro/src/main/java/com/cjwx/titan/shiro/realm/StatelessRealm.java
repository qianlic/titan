package com.cjwx.titan.shiro.realm;

import com.cjwx.titan.engine.reids.jwt.JwtHelper;
import com.cjwx.titan.engine.util.ObjectUtils;
import com.cjwx.titan.engine.util.StringUtils;
import com.cjwx.titan.shiro.StatelessAuthenticationInfo;
import com.cjwx.titan.shiro.StatelessToken;
import com.cjwx.titan.shiro.bean.SysRoleBean;
import com.cjwx.titan.shiro.bean.SysUserBean;
import com.cjwx.titan.shiro.service.ResourceService;
import com.cjwx.titan.shiro.service.RoleService;
import com.cjwx.titan.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 无状态Realm验证逻辑定义
 * @Author: qian li
 * @Date: 2018年03月29日 12:54
 */
public class StatelessRealm extends AuthorizingRealm {

    public static final String SYS_ACCOUNT = "admin";

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private ResourceService resourceService;

    /**
     * 仅支持StatelessToken类型的Token
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        StatelessToken statelessToken = (StatelessToken) token;
        String username = (String) statelessToken.getPrincipal();  //得到用户名
        if (StatelessToken.TYPE_AUTHC.equals(statelessToken.getType())) {
            Object o = JwtHelper.parseToken(username, statelessToken.getHost());
            if (ObjectUtils.isEmpty(o)) {
                throw new AuthenticationException("凭据认证失败！");
            }
            return new SimpleAuthenticationInfo(o, Boolean.TRUE, getName());
        }
        SysUserBean user = userService.findUserByCode(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new UnknownAccountException("用户名密码错误！");
        } else if (Boolean.FALSE.equals(user.getStatus())) {
            throw new LockedAccountException("账号[" + username + "]锁定！");
        }
        if (SYS_ACCOUNT.equalsIgnoreCase(username)) {
            user.setRoles(roleService.getRoleList());
            user.setResources(resourceService.getResourceList(true));
        } else {
            user.setRoles(roleService.findRolesByIds(user.getRoleids()));
            List<String> resourceIds = user.getRoles().stream()
                    .map(SysRoleBean::getResourceids)
                    .filter(StringUtils::isNotEmpty)
                    .flatMap(ids -> StringUtils.stringToList(ids).stream())
                    .distinct()
                    .collect(Collectors.toList());
            user.setResources(resourceService.findResourceByIds(resourceIds));
        }
        return new StatelessAuthenticationInfo(user, getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        StatelessToken token = (StatelessToken) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
        if (ObjectUtils.isNotEmpty(token)) {
            auth.setRoles(token.getRoles());
            auth.setStringPermissions(token.getPermissions());
        }
        return auth;
    }

}
