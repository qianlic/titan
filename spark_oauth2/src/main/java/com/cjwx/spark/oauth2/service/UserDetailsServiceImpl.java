package com.cjwx.spark.oauth2.service;

import com.cjwx.spark.oauth2.entity.OAuthAuthority;
import com.cjwx.spark.engine.entity.SysResource;
import com.cjwx.spark.engine.entity.SysRole;
import com.cjwx.spark.engine.entity.SysUser;
import com.cjwx.spark.engine.repository.UserRepository;
import com.cjwx.spark.oauth2.entity.OAuthUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年09月03日} 10:39
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public OAuthUser loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userRepository.findByUserCode(username);
        OAuthUser userDetails = new OAuthUser();
        userDetails.setUserCode(user.getUserCode());
        userDetails.setUsername(user.getUserName());
        userDetails.setPassword(user.getPassword());
        userDetails.setSalt(user.getSalt());
        List<OAuthAuthority> authorities = new ArrayList<>();
        List<SysRole> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            for (SysRole role : roles) {
                List<SysResource> resources = role.getResources();
                if (resources != null && !resources.isEmpty()) {
                    for (SysResource resource : resources) {
                        OAuthAuthority authorise = new OAuthAuthority(resource.getResourceCode());
                        if (!authorities.contains(authorise)) {
                            authorities.add(authorise);
                        }
                    }
                }
            }
        }
        userDetails.setAuthorities(authorities);
        return userDetails;
    }

}
