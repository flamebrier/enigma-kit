package com.pri.aa.enigma.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class AppUserDetails implements UserDetails {
    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;
    public AppUserDetails() {
    }

    public AppUserDetails(String username) {
        this.username = username;
    }

    public AppUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public AppUserDetails(Optional<User> user) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}