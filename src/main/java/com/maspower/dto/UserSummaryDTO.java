package com.maspower.dto;

public record UserSummaryDTO(
        Long id,
        String name,
        String surname,
        String dni,
        boolean isActive
) {}