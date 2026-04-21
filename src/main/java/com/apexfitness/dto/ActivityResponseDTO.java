package com.apexfitness.dto;

import com.apexfitness.model.Category;
import com.apexfitness.model.Intensity;
import java.time.LocalDateTime;
import java.util.Set;

public record ActivityResponseDTO(
        Long id,
        String title,
        String description,
        double price,
        LocalDateTime date,
        String imageUrl,
        Category category,
        Intensity intensity,
        int durationMinutes,
        double caloriesEstimate,
        ProfessorSummaryDTO professor,
        Set<UserSummaryDTO> users
) {}