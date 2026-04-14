package com.maspower.service;

import com.maspower.model.Activity;
import com.maspower.model.User;
import com.maspower.repository.ActivityRepository;
import com.maspower.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    public List<Activity> findFutureActivities() {
        return activityRepository.findByDateAfter(LocalDateTime.now());
    }

    public Activity findById(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));
    }

    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity update(Long id, Activity activity) {
        Activity existing = findById(id);
        existing.setTitle(activity.getTitle());
        existing.setDescription(activity.getDescription());
        existing.setPrice(activity.getPrice());
        existing.setDate(activity.getDate());
        existing.setImageUrl(activity.getImageUrl());
        existing.setProfessor(activity.getProfessor());
        return activityRepository.save(existing);
    }

    public void delete(Long id) {
        findById(id);
        activityRepository.deleteById(id);
    }

    public Activity enrollUser(Long activityId, Long userId) {
        Activity activity = findById(activityId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Criterio: usuario debe estar activo
        if (!user.isActive()) {
            throw new RuntimeException("User is not active");
        }

        // Criterio: no puede inscribirse 2 veces
        if (activity.getUsers().contains(user)) {
            throw new RuntimeException("User already enrolled in this activity");
        }

        // Criterio: máximo 3 actividades futuras
        long futureCount = activityRepository.findByDateAfter(LocalDateTime.now())
                .stream()
                .filter(a -> a.getUsers().contains(user))
                .count();

        if (futureCount >= 3) {
            throw new RuntimeException("User already has 3 future activities");
        }

        activity.getUsers().add(user);
        return activityRepository.save(activity);
    }

    public Activity unenrollUser(Long activityId, Long userId) {
        Activity activity = findById(activityId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        activity.getUsers().remove(user);
        return activityRepository.save(activity);
    }

}