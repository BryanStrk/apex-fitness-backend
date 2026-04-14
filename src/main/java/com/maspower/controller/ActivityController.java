package com.maspower.controller;

import com.maspower.dto.ActivityMapper;
import com.maspower.dto.ActivityResponseDTO;
import com.maspower.service.ActivityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping
    public ResponseEntity<List<ActivityResponseDTO>> getAll() {
        return ResponseEntity.ok(activityService.findAll().stream()
                .map(ActivityMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/future")
    public ResponseEntity<List<ActivityResponseDTO>> getFuture() {
        return ResponseEntity.ok(activityService.findFutureActivities().stream()
                .map(ActivityMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ActivityMapper.toDTO(activityService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ActivityResponseDTO> create(@Valid @RequestBody com.maspower.model.Activity activity) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ActivityMapper.toDTO(activityService.save(activity)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityResponseDTO> update(@PathVariable Long id, @Valid @RequestBody com.maspower.model.Activity activity) {
        return ResponseEntity.ok(ActivityMapper.toDTO(activityService.update(id, activity)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activityService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{activityId}/enroll/{userId}")
    public ResponseEntity<ActivityResponseDTO> enroll(@PathVariable Long activityId, @PathVariable Long userId) {
        return ResponseEntity.ok(ActivityMapper.toDTO(activityService.enrollUser(activityId, userId)));
    }

    @DeleteMapping("/{activityId}/unenroll/{userId}")
    public ResponseEntity<ActivityResponseDTO> unenroll(@PathVariable Long activityId, @PathVariable Long userId) {
        return ResponseEntity.ok(ActivityMapper.toDTO(activityService.unenrollUser(activityId, userId)));
    }
}