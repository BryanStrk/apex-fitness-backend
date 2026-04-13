package com.maspower.repository;

import com.maspower.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    boolean existsByDni(String dni);
}