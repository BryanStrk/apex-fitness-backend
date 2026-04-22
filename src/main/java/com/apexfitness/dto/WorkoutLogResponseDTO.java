package com.apexfitness.dto;

import com.apexfitness.model.Category;
import com.apexfitness.model.Intensity;

import java.time.LocalDateTime;

/**
 * DTO for returning a WorkoutLog to the client.
 *
 * Flattens the Activity reference to expose only the fields the
 * frontend needs (title, category, intensity), avoiding the need
 * to send the full Activity entity with all its relationships.
 */
public record WorkoutLogResponseDTO(
        Long id,
        Long userId,
        Long activityId,
        String activityTitle,
        Category activityCategory,
        Intensity activityIntensity,
        LocalDateTime completedAt,
        double caloriesBurned,
        int durationActualMinutes,
        String notes
) {}