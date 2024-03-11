package com.example.Auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import com.example.Auth.Service.UserInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

// qui puoi chiamare endpoint protetti solo avendo la role specifica e autorizzato tramite token, il tochen va passato sotto header come: Authorization: "Barer ...Token"
// barer è il tipo di token in questo caso barer

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserInfoService userService;

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userBoard() {
        return "Admin User";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminBoard() {
        return "Admin Board";
    }

    @GetMapping("/superAdmin")
    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    public String SuperadminBoard() {
        return "Super Admin Board";
    }
}
