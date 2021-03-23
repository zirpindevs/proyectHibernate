package com.example.proyecto5hibernate.service.impl;

import com.example.proyecto5hibernate.dao.UserDAO;
import com.example.proyecto5hibernate.model.User;
import com.example.proyecto5hibernate.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> findAllFromSession() {
        List<User> results = this.userDAO.findAllFromSession();
        System.out.println("***********");
        return results;
    }
}
