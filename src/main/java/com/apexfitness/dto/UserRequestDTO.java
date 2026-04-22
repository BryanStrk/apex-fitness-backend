package com.apexfitness.dto;

import com.apexfitness.model.Role;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Surname is required")
        String surname,

        @NotBlank(message = "DNI is required")
        String dni,

        @Min(value = 2000, message = "Registration year must be 2000 or later")
        int registrationYear,

        boolean isActive,

        String imageUrl,

        Role role
) {}