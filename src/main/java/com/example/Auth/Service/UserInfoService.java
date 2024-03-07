package com.example.Auth.Service;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Auth.Entity.UserDb;
import com.example.Auth.repos.UserRepos;


@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserRepos userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<UserDb> userDetail = userRepository.findUserByFiscalCode(username);

        if (userDetail.isEmpty())
            throw new UsernameNotFoundException(username);

        UserInfoDetails userInfoDetails = new UserInfoDetails(userDetail.get());
        return userInfoDetails;

    }

}