package com.example.proyecto5hibernate.controller;

import com.example.proyecto5hibernate.model.User;
import com.example.proyecto5hibernate.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService UserService;

    public UserController(com.example.proyecto5hibernate.service.UserService userService) {
        UserService = userService;
    }


    @GetMapping("/users")
    public List<User> findAll() {
        return UserService.findAllFromSession();
    }

}
