package com.apexfitness.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents a completed workout session by a user.
 *
 * A WorkoutLog is created when a user completes an Activity. While
 * the relationship between User and Activity (via the activity_users
 * join table) represents enrollment, a WorkoutLog represents actual
 * completion with metrics like calories burned and actual duration.
 *
 * A user can have multiple logs for the same activity (they can
 * complete the same workout multiple times).
 */
@Entity
@Table(name = "workout_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

    @Column(name = "completed_at", nullable = false)
    private LocalDateTime completedAt;

    @Column(name = "calories_burned", nullable = false)
    private double caloriesBurned;

    @Column(name = "duration_actual_minutes", nullable = false)
    private int durationActualMinutes;

    @Column(name = "notes", length = 500)
    private String notes;
}