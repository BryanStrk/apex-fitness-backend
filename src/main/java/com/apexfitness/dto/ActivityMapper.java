package com.apexfitness.dto;

import com.apexfitness.model.Activity;
import com.apexfitness.model.Professor;
import com.apexfitness.model.User;

import java.util.stream.Collectors;

public class ActivityMapper {

    // Entity → Response DTO
    public static ActivityResponseDTO toDTO(Activity activity) {
        return new ActivityResponseDTO(
                activity.getId(),
                activity.getTitle(),
                activity.getDescription(),
                activity.getPrice(),
                activity.getDate(),
                activity.getImageUrl(),
                activity.getCategory(),
                activity.getIntensity(),
                activity.getDurationMinutes(),
                activity.getCaloriesEstimate(),
                toProfessorSummary(activity.getProfessor()),
                activity.getUsers().stream()
                        .map(ActivityMapper::toUserSummary)
                        .collect(Collectors.toSet())
        );
    }

    // Request DTO → Entity (para crear)
    public static Activity toEntity(ActivityRequestDTO dto, Professor professor) {
        Activity activity = new Activity();
        activity.setTitle(dto.title());
        activity.setDescription(dto.description());
        activity.setPrice(dto.price());
        activity.setDate(dto.date());
        activity.setImageUrl(dto.imageUrl());
        activity.setCategory(dto.category());
        activity.setIntensity(dto.intensity());
        activity.setDurationMinutes(dto.durationMinutes());
        activity.setCaloriesEstimate(dto.caloriesEstimate());
        activity.setProfessor(professor);
        return activity;
    }

    // Request DTO → actualiza Entity existente (para update)
    public static void updateEntity(Activity existing, ActivityRequestDTO dto, Professor professor) {
        existing.setTitle(dto.title());
        existing.setDescription(dto.description());
        existing.setPrice(dto.price());
        existing.setDate(dto.date());
        existing.setImageUrl(dto.imageUrl());
        existing.setCategory(dto.category());
        existing.setIntensity(dto.intensity());
        existing.setDurationMinutes(dto.durationMinutes());
        existing.setCaloriesEstimate(dto.caloriesEstimate());
        existing.setProfessor(professor);
    }

    private static ProfessorSummaryDTO toProfessorSummary(Professor professor) {
        return new ProfessorSummaryDTO(
                professor.getId(),
                professor.getName(),
                professor.isActive()
        );
    }

    private static UserSummaryDTO toUserSummary(User user) {
        return new UserSummaryDTO(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getDni(),
                user.isActive()
        );
    }
}