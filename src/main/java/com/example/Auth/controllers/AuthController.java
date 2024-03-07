package com.example.Auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.Auth.DTO.UserRegistrationDTO;
import com.example.Auth.Service.UserInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AuthController {

    @Autowired
    UserInfoService userService;
   

   

    @PostMapping("/add_new_user")
    public UserRegistrationDTO addNewUser(@RequestBody UserRegistrationDTO user)
            throws Exception {
        user.setRole("ADMIN");
        return this.userService.registerUser(user);
    }



}
