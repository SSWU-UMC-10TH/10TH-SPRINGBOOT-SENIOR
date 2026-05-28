package com.example.umc.global.security;

import com.example.umc.domain.auth.entity.Auth;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final Auth auth;

    public CustomUserDetails(Auth auth) {
        this.auth = auth;
    }

    public Long getUserId() {
        return auth.getUser().getId();
    }

    public String getEmail() {
        return auth.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return auth.getPassword();
    }

    @Override
    public String getUsername() {
        return auth.getEmail();
    }
}