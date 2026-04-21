package com.maspower.service;

import com.maspower.dto.UserMapper;
import com.maspower.dto.UserRequestDTO;
import com.maspower.exception.ResourceNotFoundException;
import com.maspower.model.User;
import com.maspower.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public User save(UserRequestDTO dto) {
        User user = UserMapper.toEntity(dto);
        return userRepository.save(user);
    }

    public User update(Long id, UserRequestDTO dto) {
        User existing = findById(id);
        UserMapper.updateEntity(existing, dto);
        return userRepository.save(existing);
    }

    public void delete(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }
}