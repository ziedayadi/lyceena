package com.zka.lyceena.test.services;

import com.zka.lyceena.security.UserDetails;
import com.zka.lyceena.services.UserDetailsProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsProviderTestImpl implements UserDetailsProvider {

    @Override
    public UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = new UserDetails();
        userDetails.setUserName(authentication.getName());
        List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        userDetails.setRoles(roles);
        return userDetails;
    }
}
