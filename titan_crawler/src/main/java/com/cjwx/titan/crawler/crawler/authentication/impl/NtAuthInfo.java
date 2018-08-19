package com.cjwx.titan.crawler.crawler.authentication.impl;

import com.cjwx.titan.crawler.crawler.authentication.AuthInfo;
import lombok.Data;

import java.net.MalformedURLException;

import javax.swing.text.html.FormSubmitEvent.MethodType;

@Data
public class NtAuthInfo extends AuthInfo {

    private String domain;

    public NtAuthInfo(String username, String password, String loginUrl, String domain) throws MalformedURLException {
        super(AuthenticationType.NT_AUTHENTICATION, MethodType.GET, loginUrl, username, password);

        this.domain = domain;
    }

}
