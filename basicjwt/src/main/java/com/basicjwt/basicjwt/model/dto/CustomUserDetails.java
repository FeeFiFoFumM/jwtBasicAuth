package com.basicjwt.basicjwt.model.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.basicjwt.basicjwt.model.entity.User;

import java.util.stream.Collectors;


import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private final User user;

    CustomUserDetails(User user){
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // User entity'sindeki şifreyi döndür
    }

    @Override
    public String getUsername() {
        return user.getUsername(); // User entity'sindeki kullanıcı adını döndür
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
        return user.isEnabled(); // User entity'nizde aktif olup olmadığını döndürün
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    return user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name())) // Enum'ı String'e çeviriyoruz
        .collect(Collectors.toList());
}
    
}
