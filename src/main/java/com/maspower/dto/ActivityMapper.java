package com.maspower.dto;

import com.maspower.model.Activity;
import com.maspower.model.Professor;
import com.maspower.model.User;

import java.util.stream.Collectors;

public class ActivityMapper {

    // Entity → Response DTO
    public static ActivityResponseDTO toDTO(Activity activity) {
        ActivityResponseDTO dto = new ActivityResponseDTO();
        dto.setId(activity.getId());
        dto.setTitle(activity.getTitle());
        dto.setDescription(activity.getDescription());
        dto.setPrice(activity.getPrice());
        dto.setDate(activity.getDate());
        dto.setImageUrl(activity.getImageUrl());
        dto.setProfessor(toProfessorSummary(activity.getProfessor()));
        dto.setUsers(activity.getUsers().stream()
                .map(ActivityMapper::toUserSummary)
                .collect(Collectors.toSet()));
        return dto;
    }

    // Request DTO → Entity (para crear)
    public static Activity toEntity(ActivityRequestDTO dto, Professor professor) {
        Activity activity = new Activity();
        activity.setTitle(dto.getTitle());
        activity.setDescription(dto.getDescription());
        activity.setPrice(dto.getPrice());
        activity.setDate(dto.getDate());
        activity.setImageUrl(dto.getImageUrl());
        activity.setProfessor(professor);
        return activity;
    }

    // Request DTO → actualiza Entity existente (para update)
    public static void updateEntity(Activity existing, ActivityRequestDTO dto, Professor professor) {
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setDate(dto.getDate());
        existing.setImageUrl(dto.getImageUrl());
        existing.setProfessor(professor);
    }

    private static ProfessorSummaryDTO toProfessorSummary(Professor professor) {
        ProfessorSummaryDTO dto = new ProfessorSummaryDTO();
        dto.setId(professor.getId());
        dto.setName(professor.getName());
        dto.setActive(professor.isActive());
        return dto;
    }

    private static UserSummaryDTO toUserSummary(User user) {
        UserSummaryDTO dto = new UserSummaryDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setDni(user.getDni());
        dto.setActive(user.isActive());
        return dto;
    }
}