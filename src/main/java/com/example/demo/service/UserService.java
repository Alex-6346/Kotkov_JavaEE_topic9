package com.example.demo.service;

import com.example.demo.config.SaltStorage;
import com.example.demo.entity.PermissionEntity;
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

    public UserEntity registerUser(UserEntity user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), SaltStorage.SALT));
        PermissionEntity permission = userRepository.viewCatalogPermission().orElse(null);
        user.setPermissions(Arrays.asList(permission));
        user.setFavouriteBooks(Arrays.asList());

        UserEntity sameUser = userRepository.findByLogin(user.getLogin()).orElse(null);
        if (sameUser == null) {
            userRepository.saveAndFlush(user);
            return user;
        } else {
            return null;
        }
    }
}