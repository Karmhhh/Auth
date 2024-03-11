package com.example.Auth.DTO;

import com.example.Auth.Entity.RoleType;

public class UserRegistrationDTO {
    RoleType roleEnum;
    
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private String fiscalCode;

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {

        switch (role) {
            case "user":
                this.role = roleEnum.ROLE_USER.toString();
                break;
            case "admin":
                this.role = roleEnum.ROLE_ADMIN.toString();
                break;
            case "super_admin":
                this.role = roleEnum.ROLE_SUPERADMIN.toString();
                break;
            default: 
            this.role = roleEnum.ROLE_USER.toString();
                break;
        }

    }

}