package com.innowise.secretSanta.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.innowise.secretSanta.model.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String email;
    @JsonIgnore
    private String password;
    private GrantedAuthority authority;

    public UserDetailsImpl(Long id, String username, String password,
                           GrantedAuthority authority) {
        this.id = id;
        this.email = username;
        this.password = password;
        this.authority = authority;
    }

    public static UserDetailsImpl build(Account user) {
        GrantedAuthority authority =  new SimpleGrantedAuthority(user.getRole().getName().name());
        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authority);
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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