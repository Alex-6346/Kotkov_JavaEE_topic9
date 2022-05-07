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

    String salt = BCrypt.gensalt(10);

    @Override
    public String encode(final CharSequence rawPassword) {
        userRepository.encodePasswords(BCrypt.hashpw("password",salt));
        return BCrypt.hashpw(rawPassword.toString(),salt);
    }

    @Override
    public boolean matches(final CharSequence rawPassword, final String encodedPassword) {
        System.out.println(rawPassword);
        System.out.println(encodedPassword);
        return BCrypt.checkpw(rawPassword.toString(),encodedPassword);
    }

}
