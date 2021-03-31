package com.example.proyecto5hibernate.service;

import com.example.proyecto5hibernate.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAllFromRepository();

    Task createTask(Task task);
    Task updateTask(Long id, Task task);

    List<Task> findAll();
    Task findOne(Long id);
    List<Task> findAllByTitle(String title);

    }
