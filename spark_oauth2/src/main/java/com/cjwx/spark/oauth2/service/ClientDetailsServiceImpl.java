package com.cjwx.spark.oauth2.service;

import com.cjwx.spark.engine.entity.SysClient;
import com.cjwx.spark.oauth2.entity.OAuthClient;
import com.cjwx.spark.engine.repository.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年09月09日} 15:10
 */
@Service("clientDetailsService")
@Transactional
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Resource
    private ClientRepository clientRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        SysClient client = clientRepository.findByClientCode(clientId);
        OAuthClient clientDetails = new OAuthClient();
        clientDetails.setClientId(client.getClientCode());
        clientDetails.setClientSecret(passwordEncoder.encode(client.getClientSecret()));
        clientDetails.setAccessTokenValiditySeconds(3600);
        clientDetails.setAuthorizedGrantTypes(client.getAuthorizedGrantTypes());
        clientDetails.setScope(client.getScopes());
        return clientDetails;
    }

}
