package com.maspower.repository;

import com.maspower.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByDni(String dni);
}