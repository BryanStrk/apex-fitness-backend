package com.maspower.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Surname is required")
    private String surname;

    @NotBlank(message = "DNI is required")
    private String dni;

    @Min(value = 2000, message = "Registration year must be 2000 or later")
    private int registrationYear;

    private boolean isActive;

    private String imageUrl;
}