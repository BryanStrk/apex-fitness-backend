package com.maspower.dto;

import com.maspower.model.Activity;
import com.maspower.model.Professor;
import com.maspower.model.User;

import java.util.stream.Collectors;

public class ActivityMapper {

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