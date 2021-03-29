package com.example.proyecto5hibernate.repository;

import com.example.proyecto5hibernate.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
