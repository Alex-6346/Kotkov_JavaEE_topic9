package com.example.demo.service;

import com.example.demo.config.SaltStorage;
import com.example.demo.entity.PermissionEntity;
import com.example.demo.entity.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity registerUser(UserDto user) {
        PermissionEntity permission = userRepository.viewCatalogPermission().orElse(null);
        UserEntity newUser = new UserEntity(user.getLogin(),"temp",Arrays.asList(permission),Arrays.asList());
        newUser.setPassword(BCrypt.hashpw(user.getPassword(), SaltStorage.SALT));

        UserEntity sameUser = userRepository.findByLogin(user.getLogin()).orElse(null);
        if (sameUser == null) {
            userRepository.saveAndFlush(newUser);
            return newUser;
        } else {
            return null;
        }
    }
}