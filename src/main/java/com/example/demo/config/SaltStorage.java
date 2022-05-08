package com.example.demo.config;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class SaltStorage {
    public final static String SALT = BCrypt.gensalt(10);
}
