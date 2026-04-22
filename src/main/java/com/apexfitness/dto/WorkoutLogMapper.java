package com.apexfitness.dto;

import com.apexfitness.model.Activity;
import com.apexfitness.model.User;
import com.apexfitness.model.WorkoutLog;

import java.time.LocalDateTime;

/**
 * Maps between WorkoutLog entity and its DTOs.
 */
public class WorkoutLogMapper {

    /**
     * Convert entity → response DTO.
     */
    public static WorkoutLogResponseDTO toDTO(WorkoutLog log) {
        return new WorkoutLogResponseDTO(
                log.getId(),
                log.getUser().getId(),
                log.getActivity().getId(),
                log.getActivity().getTitle(),
                log.getActivity().getCategory(),
                log.getActivity().getIntensity(),
                log.getCompletedAt(),
                log.getCaloriesBurned(),
                log.getDurationActualMinutes(),
                log.getNotes()
        );
    }

    /**
     * Convert request DTO → new entity.
     *
     * Applies defaults from the Activity when the DTO has null values:
     * - caloriesBurned → activity.caloriesEstimate
     * - durationActualMinutes → activity.durationMinutes
     * - completedAt → LocalDateTime.now()
     */
    public static WorkoutLog toEntity(WorkoutLogRequestDTO dto, User user, Activity activity) {
        WorkoutLog log = new WorkoutLog();
        log.setUser(user);
        log.setActivity(activity);
        log.setCompletedAt(dto.completedAt() != null ? dto.completedAt() : LocalDateTime.now());
        log.setCaloriesBurned(dto.caloriesBurned() != null ? dto.caloriesBurned() : activity.getCaloriesEstimate());
        log.setDurationActualMinutes(dto.durationActualMinutes() != null ? dto.durationActualMinutes() : activity.getDurationMinutes());
        log.setNotes(dto.notes());
        return log;
    }
}