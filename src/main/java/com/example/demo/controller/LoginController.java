package com.example.demo.controller;
import com.example.demo.entity.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String registerPage(Model model){
        return "book-register";
    }

    @PostMapping
    @ResponseBody
    public UserEntity createUser(@Valid @RequestBody UserDto user){
        return  userService.registerUser(user);
    }



}
