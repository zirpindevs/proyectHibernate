package com.example.proyecto5hibernate.dao;

import com.example.proyecto5hibernate.model.User;

import java.util.List;

public interface UserDAO {

    List<User> findAllFromSession();
    List<User> findAllFromRepository();

}
