package com.apexfitness.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * DTO for creating a new WorkoutLog (registering a completed workout).
 *
 * Optional fields (caloriesBurned, durationActualMinutes, completedAt)
 * fall back to sensible defaults in the service layer when null:
 * - caloriesBurned defaults to activity.caloriesEstimate
 * - durationActualMinutes defaults to activity.durationMinutes
 * - completedAt defaults to LocalDateTime.now()
 */
public record WorkoutLogRequestDTO(

        @NotNull(message = "User id is required")
        Long userId,

        @NotNull(message = "Activity id is required")
        Long activityId,

        LocalDateTime completedAt,

        @PositiveOrZero(message = "Calories burned must be zero or positive")
        Double caloriesBurned,

        @Positive(message = "Duration must be positive")
        Integer durationActualMinutes,

        @Size(max = 500, message = "Notes cannot exceed 500 characters")
        String notes
) {}