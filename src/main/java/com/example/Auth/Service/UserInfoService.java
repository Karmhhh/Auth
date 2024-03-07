package com.example.Auth.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            userRepository.deleteById(id);
            map.put("status", 1);
            map.put("message", "Record is deleted successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
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

    public ResponseEntity<String> changePassword(Long id, String oldPass, String pass) {

        UserDb user = userRepository.findById(id).get();
        if (user != null && user.getPassword() == encoder.encode(oldPass)) {
            user.setPassword(encoder.encode(pass));
            return new ResponseEntity<>("Success!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error, The old Password don't Metch or User Not Found! Try Again.",
                    HttpStatus.NOT_FOUND);
        }
    }
}