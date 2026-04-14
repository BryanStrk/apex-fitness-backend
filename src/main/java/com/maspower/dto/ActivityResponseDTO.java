package com.maspower.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ActivityResponseDTO {
    private Long id;
    private String title;
    private String description;
    private double price;
    private LocalDateTime date;
    private String imageUrl;
    private ProfessorSummaryDTO professor;
    private Set<UserSummaryDTO> users;
}