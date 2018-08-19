package com.cjwx.titan.crawler.crawler.authentication.impl;

import com.cjwx.titan.crawler.crawler.authentication.AuthInfo;
import lombok.Data;

import java.net.MalformedURLException;

import javax.swing.text.html.FormSubmitEvent.MethodType;

@Data
public class FormAuthInfo extends AuthInfo {

    private String usernameFormStr;
    private String passwordFormStr;
    private String captchaFormStr;
    private String captcha;

    public FormAuthInfo(String username, String password, String loginUrl, String usernameFormStr, String passwordFormStr) throws MalformedURLException {
        super(AuthenticationType.FORM_AUTHENTICATION, MethodType.POST, loginUrl, username, password);

        this.usernameFormStr = usernameFormStr;
        this.passwordFormStr = passwordFormStr;
    }

    public FormAuthInfo(String username, String password, String captcha, String loginUrl, String usernameFormStr, String passwordFormStr, String captchaFormStr) throws MalformedURLException {
        this(username, password, loginUrl, usernameFormStr, passwordFormStr);

        this.captchaFormStr = captchaFormStr;
        this.captcha = captcha;
    }

}
