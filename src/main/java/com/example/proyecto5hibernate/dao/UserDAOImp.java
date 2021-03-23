package com.example.proyecto5hibernate.dao;

import com.example.proyecto5hibernate.model.User;
import com.example.proyecto5hibernate.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImp implements UserDAO {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Session session;

    @Autowired
    private UserRepository repository;


    @Override
    public List<User> findAllFromSession() {
        return session.createQuery("from User e").list();
    }
}
