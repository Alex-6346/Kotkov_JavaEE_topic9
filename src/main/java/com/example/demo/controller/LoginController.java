package com.example.demo.controller;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public UserEntity createUser(@RequestBody UserEntity user){
        System.out.println("User registered!");
        UserEntity registeredUser = userService.registerUser(user);
        System.out.println("User registered!");
        return registeredUser;
    }



}
