package com.example.proyecto5hibernate.service.impl;

import com.example.proyecto5hibernate.dao.TaskDAO;
import com.example.proyecto5hibernate.model.Task;
import com.example.proyecto5hibernate.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskDAO taskDAO;

    public TaskServiceImpl(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }


/*    @Override
    public List<Task> findAllFromSession() {
        List<Task> results = this.taskDAO.findAllFromSession();
        System.out.println("***********");
        return results;
    }*/

    @Override
    public List<Task> findAllFromRepository() {
        List<Task> results = this.taskDAO.findAllFromRepository();
        System.out.println("***********");
        return results;
    }

    @Override
    public Task createTask(Task task) {
        log.info("REST request to create a task");
        return this.taskDAO.createTask(task);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        log.info("REST request to update an task");

            Task findTask = this.findOne(id);

        if(findTask == null) {
            return null;
        }

        return this.taskDAO.modifyTask(task, findTask);
    }

    @Override
    public List<Task> findAll() {
        log.info("REST request to find all tasks");

        return this.taskDAO.findAll();
    }

    @Override
    public Task findOne(Long id) {
        log.info("REST request to find one task by id");

        if(id == null)
            return null;
        return this.taskDAO.findById(id);
    }

    @Override
    public List<Task> findAllByTitle(String title) {
        log.info("REST request to find an task by title");

        if(title.isEmpty())
            return null;
        return this.taskDAO.findAllByTitle(title);
    }


}
