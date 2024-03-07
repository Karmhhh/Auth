package com.example.Auth.DTO;

import org.springframework.beans.factory.annotation.Autowired;

public class UserRegistrationDTO {
    @Autowired
    private String nominative;
    public String getNominative() {
        return nominative;
    }
    public void setNominative(String nominative) {
        this.nominative = nominative;
    }
    @Autowired
    private String phoneNumber;
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Autowired
    private String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Autowired
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Autowired
    private String roles;
    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }

}