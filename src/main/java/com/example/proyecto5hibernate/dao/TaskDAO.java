package com.example.proyecto5hibernate.dao;

import com.example.proyecto5hibernate.model.Task;

import java.util.List;

public interface TaskDAO {

    List<Task> findAllFromSession();
    List<Task> findAllFromRepository();
    List<Task> findAll();
    Task findById(Long id);
    Task findByTitle(String title);
    Task createTask(Task task);
    Task modifyTask(Task task, Task findedTask);



}
