package com.maspower.service;

import com.maspower.dto.ProfessorMapper;
import com.maspower.dto.ProfessorRequestDTO;
import com.maspower.exception.ResourceNotFoundException;
import com.maspower.model.Professor;
import com.maspower.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Professor findById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found with id: " + id));
    }

    public Professor save(ProfessorRequestDTO dto) {
        Professor professor = ProfessorMapper.toEntity(dto);
        return professorRepository.save(professor);
    }

    public Professor update(Long id, ProfessorRequestDTO dto) {
        Professor existing = findById(id);
        ProfessorMapper.updateEntity(existing, dto);
        return professorRepository.save(existing);
    }

    public void delete(Long id) {
        findById(id);
        professorRepository.deleteById(id);
    }
}