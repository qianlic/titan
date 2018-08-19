package com.cjwx.titan.crawler.crawler.authentication.impl;

import com.cjwx.titan.crawler.crawler.authentication.AuthInfo;

import java.net.MalformedURLException;

import javax.swing.text.html.FormSubmitEvent.MethodType;

public class BasicAuthInfo extends AuthInfo {

    public BasicAuthInfo(String username, String password, String loginUrl) throws MalformedURLException {
        super(AuthenticationType.BASIC_AUTHENTICATION, MethodType.GET, loginUrl, username, password);
    }

}
