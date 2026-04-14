package com.maspower.dto;

import lombok.Data;

@Data
public class UserSummaryDTO {
    private Long id;
    private String name;
    private String surname;
    private String dni;
    private boolean isActive;
}