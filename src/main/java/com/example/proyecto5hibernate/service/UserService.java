package com.example.proyecto5hibernate.service;

import com.example.proyecto5hibernate.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllFromSession();

}
