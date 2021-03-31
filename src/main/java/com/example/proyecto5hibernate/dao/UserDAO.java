package com.example.proyecto5hibernate.dao;

import com.example.proyecto5hibernate.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    List<User> findAllFromSession();
    List<User> findAllFromRepository();
    List<User> findAll();
    User findById(Long id);
    User findByName(String name);
    User createUser(User user);
    User modifyUser(User user, User findedUser);



}
