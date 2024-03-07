package com.example.Auth.Entity;
import org.springframework.lang.NonNull;

import jakarta.persistence.*;

@Entity
@Table(name = "user-data")
public class UserDb {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user-id")
    private Long userId;
    @NonNull
    @Column(name = "fiscal-code", unique = true)
    private String fiscalCode;
    @NonNull
    @Column(name = "password")
    private String password;
    @NonNull
    @Column(name = "role")
    private String role;
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getFiscalCode() {
        return fiscalCode;
    }
    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }


    
}