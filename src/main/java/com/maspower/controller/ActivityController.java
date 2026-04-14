package com.maspower.controller;

import com.maspower.model.Activity;
import com.maspower.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping
    public ResponseEntity<List<Activity>> getAll() {
        return ResponseEntity.ok(activityService.findAll());
    }

    @GetMapping("/future")
    public ResponseEntity<List<Activity>> getFuture() {
        return ResponseEntity.ok(activityService.findFutureActivities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(activityService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Activity> create(@RequestBody Activity activity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(activityService.save(activity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activity> update(@PathVariable Long id, @RequestBody Activity activity) {
        return ResponseEntity.ok(activityService.update(id, activity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activityService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{activityId}/enroll/{userId}")
    public ResponseEntity<Activity> enroll(@PathVariable Long activityId, @PathVariable Long userId) {
        return ResponseEntity.ok(activityService.enrollUser(activityId, userId));
    }

    @DeleteMapping("/{activityId}/unenroll/{userId}")
    public ResponseEntity<Activity> unenroll(@PathVariable Long activityId, @PathVariable Long userId) {
        return ResponseEntity.ok(activityService.unenrollUser(activityId, userId));
    }


}