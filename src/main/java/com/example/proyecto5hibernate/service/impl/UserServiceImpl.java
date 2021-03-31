package com.example.proyecto5hibernate.service.impl;

import com.example.proyecto5hibernate.dao.UserDAO;
import com.example.proyecto5hibernate.model.User;
import com.example.proyecto5hibernate.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

/*    @Override
    public List<User> findAllFromSession() {
        List<User> results = this.userDAO.findAllFromSession();
        System.out.println("***********");
        return results;
    }*/

    @Override
    public List<User> findAllFromRepository() {
        List<User> results = this.userDAO.findAllFromRepository();
        System.out.println("***********");
        return results;
    }

    @Override
    public User createUser(User user) {
        log.info("REST request to create an user");
        return this.userDAO.createUser(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        log.info("REST request to update an user");

        User findUser = this.findOne(id);

        if(findUser == null) {
            return null;
        }

        return this.userDAO.modifyUser(user, findUser);
    }

    @Override
    public List<User> findAll() {
        log.info("REST request to find all users");

        return this.userDAO.findAll();
    }

    @Override
    public User findOne(Long id) {
        log.info("REST request to find one user by id");

        if(id == null)
            return null;
        return this.userDAO.findById(id);
    }

    @Override
    public List<User> findAllByName(String name) {
        log.info("REST request to find an user by name");

        if(name.isEmpty())
            return null;
        return this.userDAO.findAllByName(name);
    }


}
