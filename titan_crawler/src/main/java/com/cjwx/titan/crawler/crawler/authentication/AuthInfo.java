package com.cjwx.titan.crawler.crawler.authentication;

import lombok.Data;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.text.html.FormSubmitEvent.MethodType;

@Data
public abstract class AuthInfo {

    public enum AuthenticationType {
        BASIC_AUTHENTICATION, FORM_AUTHENTICATION, NT_AUTHENTICATION
    }

    protected AuthenticationType authenticationType;
    protected MethodType httpMethod;
    protected String protocol;
    protected String host;
    protected String loginTarget;
    protected int port;
    protected String username;
    protected String password;

    protected AuthInfo(AuthenticationType authenticationType, MethodType httpMethod,
                       String loginUrl, String username, String password) throws MalformedURLException {
        this.authenticationType = authenticationType;
        this.httpMethod = httpMethod;
        URL url = new URL(loginUrl);
        this.protocol = url.getProtocol();
        this.host = url.getHost();
        this.port = url.getDefaultPort();
        this.loginTarget = url.getFile();
        this.username = username;
        this.password = password;
    }

}
