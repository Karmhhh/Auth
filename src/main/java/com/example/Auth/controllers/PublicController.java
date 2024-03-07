package com.example.Auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Auth.DTO.UserRegistrationDTO;
import com.example.Auth.Service.JwtService;
import com.example.Auth.Service.UserInfoService;
import com.example.Auth.config.filtri.AuthRequest;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    UserInfoService userService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/generate_token")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getFiscalCode(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getFiscalCode());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Void> saveUser(@RequestBody UserRegistrationDTO user) {
        userService.registerUser(user);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/hello")
    public String helloWorld(){
        return "Hello World";
    }
}
