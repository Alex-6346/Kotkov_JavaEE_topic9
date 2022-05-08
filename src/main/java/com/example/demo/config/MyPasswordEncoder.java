package com.example.demo.config;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyPasswordEncoder implements PasswordEncoder {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String encode(final CharSequence rawPassword) {
        userRepository.encodePasswords(BCrypt.hashpw("password",SaltStorage.SALT));
        return BCrypt.hashpw(rawPassword.toString(),SaltStorage.SALT);
    }

    @Override
    public boolean matches(final CharSequence rawPassword, final String encodedPassword) {
        System.out.println(rawPassword);
        System.out.println(encodedPassword);
        return BCrypt.checkpw(rawPassword.toString(),encodedPassword);
    }

}
