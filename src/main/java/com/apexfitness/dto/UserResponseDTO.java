package com.apexfitness.dto;

import com.apexfitness.model.Role;

public record UserResponseDTO(
        Long id,
        String name,
        String surname,
        String dni,
        int registrationYear,
        boolean isActive,
        String imageUrl,
        Role role
) {}