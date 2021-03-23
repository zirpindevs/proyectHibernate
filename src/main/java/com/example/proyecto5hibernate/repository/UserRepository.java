package com.example.proyecto5hibernate.repository;

import com.example.proyecto5hibernate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}


