package com.example.proyecto5hibernate.service;

import com.example.proyecto5hibernate.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAllFromRepository();

    User createUser(User user);
    User updateUser(Long id, User user);

    List<User> findAll();
    User findOne(Long id);
    User findByName(String name);

    }
