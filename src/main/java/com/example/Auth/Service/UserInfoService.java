package com.example.Auth.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.Auth.DTO.UserRegistrationDTO;
import com.example.Auth.Entity.UserDb;
import com.example.Auth.repos.UserRepos;

@Service
public class UserInfoService implements UserDetailsService {
    Logger LOG;
    @Autowired
    private UserRepos userRepository;
    List<UserRegistrationDTO> usersDto;

    UserRegistrationDTO userDto;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDb> userDetail = userRepository.findUserByFiscalCode(username);

        if (userDetail.isEmpty())
            throw new UsernameNotFoundException(username);

        UserInfoDetails userInfoDetails = new UserInfoDetails(userDetail.get());
        return userInfoDetails;

    }

    public ResponseEntity<String> registerUser(UserRegistrationDTO user) {
        if (user != null) {
            UserDb newUser = new UserDb();
            newUser.setFiscalCode(user.getFiscalCode());
            newUser.setPassword(encoder.encode(user.getPassword()));
            newUser.setRole(user.getRole());
            userRepository.save(newUser);
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    public ResponseEntity<String> deleteAll() {
        userRepository.deleteAll();
        return ResponseEntity.status(201).build();
    }

    public ResponseEntity<?> deleteById(Long id) {
        try {
            userRepository.deleteById(id);
            return ResponseEntity.status(201).build();
        } catch (Exception ex) {
            return ResponseEntity.status(404).build();
        }
    }

    public List<UserRegistrationDTO> getAll() {
        List<UserDb> users = userRepository.findAll();
        for (UserDb userDb : users) {
            userDto.setFiscalCode(userDb.getFiscalCode());
            userDto.setRole(userDb.getRole());
            userDto.setUserId(userDb.getUserId());
            usersDto.add(userDto);
        }
        return (usersDto);

    }

    public ResponseEntity<String> changePassword(String username, String oldPass, String pass) {
        UserDb userDetail = userRepository.findUserByFiscalCode(username).get();
       
        if ( userDetail.getPassword().toString().equals(encoder.encode(oldPass).toString())) {
                userDetail.setPassword(encoder.encode(pass));
                userRepository.save(userDetail);
               
                return ResponseEntity.ok().build();
            } else {
               
                return ResponseEntity.badRequest().build();
            }
    }

}