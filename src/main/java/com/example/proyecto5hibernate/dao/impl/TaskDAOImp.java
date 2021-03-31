package com.example.proyecto5hibernate.dao.impl;

import com.example.proyecto5hibernate.dao.TaskDAO;
import com.example.proyecto5hibernate.model.Task;
import com.example.proyecto5hibernate.model.User;
import com.example.proyecto5hibernate.repository.TaskRepository;
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
public class TaskDAOImp implements TaskDAO {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Session session;

    @Autowired
    private TaskRepository repository;


    @Override
    public List<Task> findAllFromSession() {
        return session.createQuery("from Task t").list();
    }


    @Override
    public List<Task> findAllFromRepository() {
        return repository.findAll();
    }

    @Override
    public Task createTask(Task task) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        task.setFinishDate(Instant.now());
        session.save(task);

        session.getTransaction().commit();

        session.close();

        return task;
    }

    @Override
    public Task modifyTask(Task modifiedTask, Task findedTask) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        findedTask.setTitle(modifiedTask.getTitle());
        findedTask.setDescription(modifiedTask.getDescription());
        findedTask.setFinished(modifiedTask.getFinished());

        session.update(findedTask);
        session.getTransaction().commit();
        session.close();

        return findedTask;
    }

    @Override
    public List<Task> findAll(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        return session.createQuery("from Task", Task.class ).list();
    }
    @Override
    public Task findById(Long id){

        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
        Root<Task> root = criteria.from(Task.class);

        criteria.where(builder.equal(root.get("id"), id));

        Task task = session.createQuery(criteria).uniqueResult();

        session.close();

        return task;
    }

    @Override
    public List<Task> findAllByTitle(String title) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
        Root<Task> root = criteria.from(Task.class);

        criteria.select(root);
        criteria.where(builder.like(root.get("title"), "%"+title+"%"));

        List<Task> task = session.createQuery(criteria).list();

        session.close();

        return task;
    }

}
