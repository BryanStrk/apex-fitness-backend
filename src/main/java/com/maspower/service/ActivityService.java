package com.maspower.service;

import com.maspower.model.Activity;
import com.maspower.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

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
}