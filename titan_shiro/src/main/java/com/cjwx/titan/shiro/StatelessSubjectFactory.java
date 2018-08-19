package com.cjwx.titan.shiro;

import com.cjwx.titan.engine.reids.jwt.JwtToken;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * @Description: 无状态Subject工厂
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
public class StatelessSubjectFactory extends DefaultWebSubjectFactory {

    /**
     * 无状态token，不创建session
     */
    @Override
    public Subject createSubject(SubjectContext context) {
        AuthenticationToken token = context.getAuthenticationToken();
        if (token instanceof JwtToken) {
            context.setSessionCreationEnabled(false);
        }
        return super.createSubject(context);
    }

}
