package com.apexfitness.dto;

import com.apexfitness.model.Category;
import com.apexfitness.model.Intensity;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record ActivityRequestDTO(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Description is required")
        String description,

        @Positive(message = "Price must be positive")
        double price,

        @NotNull(message = "Date is required")
        @Future(message = "Date must be in the future")
        LocalDateTime date,

        String imageUrl,

        @NotNull(message = "Category is required")
        Category category,

        @NotNull(message = "Intensity is required")
        Intensity intensity,

        @Positive(message = "Duration must be positive")
        int durationMinutes,

        @PositiveOrZero(message = "Calories estimate must be zero or positive")
        double caloriesEstimate,

        @NotNull(message = "Professor id is required")
        Long professorId
) {}