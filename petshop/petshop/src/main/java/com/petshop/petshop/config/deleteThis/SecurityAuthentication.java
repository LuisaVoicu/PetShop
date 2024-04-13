package com.petshop.petshop.config.deleteThis;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class SecurityAuthentication implements Authentication {

    private static final long serialVersionUID = 1L;
    private final boolean isAuthenticated;
    private final String name;
    private final String password;
    private final SecurityUser securityUser;
    private final Collection<GrantedAuthority> authorities;


    private SecurityAuthentication(Collection<GrantedAuthority> authorities, String name, SecurityUser securityUser, String password) {
        this.authorities = authorities;
        this.name = name;
        this.password = password;
        this.securityUser = securityUser;
        this.isAuthenticated = password == null;
    }

    public static SecurityAuthentication unauthenticated(String name, String password) {
        return new SecurityAuthentication(Collections.emptyList(), name, null, password);
    }

    public static SecurityAuthentication authenticated(SecurityUser myUser) {
        return new SecurityAuthentication(myUser.getAuthorities(), myUser.getUsername(), myUser, null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return securityUser;
    }

    @Override
    public boolean isAuthenticated() {
        return  isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("Set Authenticated exception.");
    }

    @Override
    public String getName() {
        return null;
    }
}
