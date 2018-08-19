package com.cjwx.titan.shiro;

import com.cjwx.titan.shiro.bean.SysUserBean;
import lombok.Data;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

import java.io.Serializable;

/**
 * @Description: 序列化认证
 * @Author: qian li
 * @Date: 2018年03月29日 19:09
 */
@Data
public class StatelessAuthenticationInfo extends SimpleAuthenticationInfo implements Serializable {

    private static final long serialVersionUID = -9208167267130159971L;

    private SysUserBean sysUserBean;

    public StatelessAuthenticationInfo(SysUserBean user, String realmName) {
        super(user.getUsercode(), user.getPassword(), new StatelessByteSource(user.getSalt()), realmName);
        this.sysUserBean = user;
    }

}
