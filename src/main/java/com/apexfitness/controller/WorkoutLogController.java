package com.apexfitness.controller;

import com.apexfitness.dto.WorkoutLogRequestDTO;
import com.apexfitness.dto.WorkoutLogResponseDTO;
import com.apexfitness.service.WorkoutLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-logs")
@RequiredArgsConstructor
public class WorkoutLogController {

    private final WorkoutLogService workoutLogService;

    /**
     * POST /api/workout-logs
     * Register a completed workout.
     */
    @PostMapping
    public ResponseEntity<WorkoutLogResponseDTO> createLog(@Valid @RequestBody WorkoutLogRequestDTO dto) {
        WorkoutLogResponseDTO created = workoutLogService.createLog(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * GET /api/workout-logs/{id}
     * Returns a single log by id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutLogResponseDTO> getLogById(@PathVariable Long id) {
        return ResponseEntity.ok(workoutLogService.getLogById(id));
    }

    /**
     * DELETE /api/workout-logs/{id}
     * Removes a log (e.g. logged by mistake).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long id) {
        workoutLogService.deleteLog(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/workout-logs/users/{userId}
     * Returns the full history of logs for a user, most recent first.
     *
     * Note: route is nested under /api/workout-logs/users/{userId}
     * (and not /api/users/{userId}/workout-logs) to keep all WorkoutLog
     * endpoints under a single controller.
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<WorkoutLogResponseDTO>> getLogsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(workoutLogService.getLogsByUser(userId));
    }
}