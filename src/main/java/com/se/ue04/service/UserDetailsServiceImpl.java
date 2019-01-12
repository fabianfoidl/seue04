package com.se.ue04.service;

import com.se.ue04.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    FrontendService frontendService;
    private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    public void setFrontendService(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = frontendService.getUserByEmail(userName);

        if (user == null) {
            LOG.info("User " + userName + " not found");
            throw new UsernameNotFoundException("User " + userName + " not found");
        }

        LOG.info("User " + userName + " found!");

        // [ROLE_USER, ROLE_ADMIN,...]
        String role = user.getRole();

        List<GrantedAuthority> grantList = new ArrayList<>();
        if(role != null) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            grantList.add(authority);
        }

        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), grantList);

        return userDetails;
    }
}
