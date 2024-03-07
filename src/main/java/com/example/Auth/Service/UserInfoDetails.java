package com.example.Auth.Service;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.Auth.Entity.UserDb;


public class UserInfoDetails implements UserDetails {

    private String fiscalCode;
    private String password;
    private List<GrantedAuthority> authorities;

 public UserInfoDetails(UserDb userInfo){
        this.fiscalCode = userInfo.getFiscalCode();
        this.password = userInfo.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority(userInfo.getRole()));
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.fiscalCode;
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