package com.example.proyecto5hibernate.dao.impl;

import com.example.proyecto5hibernate.dao.UserDAO;
import com.example.proyecto5hibernate.model.User;
import com.example.proyecto5hibernate.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.Instant;
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


    @Override
    public List<User> findAllFromRepository() {
        return repository.findAll();
    }

    @Override
    public User createUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        user.setCreatedDate(Instant.now());
        session.save(user);

        session.getTransaction().commit();

        session.close();

        return user;
    }

    @Override
    public User modifyUser(User modifiedUser, User findedUser) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        findedUser.setName(modifiedUser.getName());
        findedUser.setSurname(modifiedUser.getSurname());
        findedUser.setDni(modifiedUser.getDni());
        findedUser.setActive(modifiedUser.getActive());

        session.update(findedUser);
        session.getTransaction().commit();
        session.close();

        return findedUser;
    }

    @Override
    public List<User> findAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();

        return session.createQuery("from User", User.class ).list();

    }
    @Override
    public User findById(Long id){

        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);

        criteria.where(builder.equal(root.get("id"), id));

        User user = session.createQuery(criteria).uniqueResult();

        session.close();

        return user;
    }

    @Override
    public List<User> findAllByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);

        criteria.select(root);
        criteria.where(builder.like(root.get("name"), "%"+name+"%"));

        List<User> user = session.createQuery(criteria).list();

        session.close();

        return user;
    }

}
