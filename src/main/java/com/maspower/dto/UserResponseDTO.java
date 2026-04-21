package com.maspower.dto;

public record UserResponseDTO(
        Long id,
        String name,
        String surname,
        String dni,
        int registrationYear,
        boolean isActive,
        String imageUrl
) {}