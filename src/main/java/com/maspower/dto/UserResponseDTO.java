package com.maspower.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String surname;
    private String dni;
    private int registrationYear;
    private boolean isActive;
    private String imageUrl;
}