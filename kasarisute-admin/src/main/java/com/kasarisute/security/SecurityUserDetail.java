package com.kasarisute.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUserDetail implements UserDetails {
    private SecurityUser user;

    public SecurityUserDetail(SecurityUser user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>() {
            {
                String[] rolesList = user.getRoles().split(",");
                for (String roleItem : rolesList) {
                    add(new SimpleGrantedAuthority(roleItem));
                }
            }
        };
    }

}
