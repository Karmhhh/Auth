package com.example.Auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Auth.DTO.UserRegistrationDTO;
import com.example.Auth.Entity.UserDb;
import com.example.Auth.Service.JwtService;
import com.example.Auth.Service.UserInfoService;
import com.example.Auth.config.filtri.AuthRequest;
import com.example.Auth.repos.UserRepos;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    UserInfoService userService;
    @Autowired
    UserRepos userRepo;
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

    @PostMapping("/registerUser")
    public ResponseEntity<String> saveUser(@RequestBody UserRegistrationDTO user) {
           return  userService.registerUser(user);
 
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(String username, String oldPass, String newPass) {
        UserDb user = userRepo.findUserByFiscalCode(username).get();
        return userService.changePassword(user.getUserId(), oldPass, newPass);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        return userService.deleteAll();
    }

    @DeleteMapping("/deleteById{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/sayHello")
    public String helloWorld() {
        return "Hello World";
    }

}
