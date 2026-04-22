package com.apexfitness.config;

import com.apexfitness.model.*;
import com.apexfitness.repository.ActivityRepository;
import com.apexfitness.repository.ProfessorRepository;
import com.apexfitness.repository.UserRepository;
import com.apexfitness.repository.WorkoutLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Seeds the database with initial workouts, a default coach,
 * a demo user and a 3-month history of completed workouts.
 *
 * Runs only if the database is empty.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final ActivityRepository activityRepository;
    private final ProfessorRepository professorRepository;
    private final UserRepository userRepository;
    private final WorkoutLogRepository workoutLogRepository;

    private final Random random = new Random(42); // Seed fijo para resultados reproducibles

    @Override
    public void run(String... args) {
        if (activityRepository.count() > 0) {
            log.info("Database already seeded, skipping DataLoader.");
            return;
        }

        log.info("Seeding database with initial data...");

        // ═══════════════════════════════════════════════════
        // 1. Crear un profesor por defecto
        // ═══════════════════════════════════════════════════
        Professor marcus = new Professor();
        marcus.setName("Marcus Kane");
        marcus.setDni("00000001A");
        marcus.setHiringYear(2020);
        marcus.setActive(true);
        marcus.setImageUrl(null);
        marcus = professorRepository.save(marcus);

        log.info("Created default coach: {}", marcus.getName());
// ═══════════════════════════════════════════════════
        // 2. Crear un usuario cliente de prueba
        // ═══════════════════════════════════════════════════
        User demoUser = new User();
        demoUser.setName("John");
        demoUser.setSurname("Drake");
        demoUser.setDni("12345678Z");
        demoUser.setRegistrationYear(2024);
        demoUser.setActive(true);
        demoUser.setImageUrl(null);
        demoUser.setRole(Role.CLIENT);
        demoUser = userRepository.save(demoUser);

        log.info("Created demo client user: {} {} (role: {})",
                demoUser.getName(), demoUser.getSurname(), demoUser.getRole());

        // ═══════════════════════════════════════════════════
        // 2b. Crear un usuario admin de prueba
        // ═══════════════════════════════════════════════════
        User adminUser = new User();
        adminUser.setName("Sarah");
        adminUser.setSurname("Admin");
        adminUser.setDni("00000002A");
        adminUser.setRegistrationYear(2023);
        adminUser.setActive(true);
        adminUser.setImageUrl(null);
        adminUser.setRole(Role.ADMIN);
        adminUser = userRepository.save(adminUser);

        log.info("Created admin user: {} {} (role: {})",
                adminUser.getName(), adminUser.getSurname(), adminUser.getRole());
        // ═══════════════════════════════════════════════════
        // 3. Crear los 8 workouts del catálogo
        // ═══════════════════════════════════════════════════
        List<Activity> workouts = List.of(
                createWorkout("METABOLIC BREAKDOWN", "High-intensity interval training for maximum caloric burn",
                        Category.HIIT, Intensity.EXTREME, 45, 650,
                        "https://res.cloudinary.com/dutmn3xde/image/upload/v1776594314/category_hiit_wpndxv.jpg",
                        marcus),
                createWorkout("POWER YOGA FLOW", "Build strength and flexibility through dynamic sequences",
                        Category.YOGA, Intensity.MODERATE, 60, 380,
                        "https://res.cloudinary.com/dutmn3xde/image/upload/v1776611489/category_yoga_hwftfm.jpg",
                        marcus),
                createWorkout("CROSSFIT BEAST MODE", "Functional movements at high intensity",
                        Category.STRENGTH, Intensity.EXTREME, 50, 720,
                        "https://res.cloudinary.com/dutmn3xde/image/upload/v1776611570/category_strength_nsba3a.jpg",
                        marcus),
                createWorkout("CARDIO RUSH", "Explosive cardio for endurance and stamina",
                        Category.CARDIO, Intensity.HARD, 35, 520,
                        "https://res.cloudinary.com/dutmn3xde/image/upload/v1776611721/gategory_cardio_at9k5v.jpg",
                        marcus),
                createWorkout("IRON FOUNDRY", "Pure strength training with progressive overload",
                        Category.STRENGTH, Intensity.HARD, 55, 480,
                        "https://res.cloudinary.com/dutmn3xde/image/upload/v1776611725/category_strenght_hard_ydduaa.jpg",
                        marcus),
                createWorkout("COMBAT STRIKE", "Boxing combinations and explosive power",
                        Category.COMBAT, Intensity.HARD, 40, 580,
                        "https://res.cloudinary.com/dutmn3xde/image/upload/v1776611827/catgory_combat_hard_tgrgsb.jpg",
                        marcus),
                createWorkout("PILATES PRECISION", "Core-focused flexibility and controlled movements",
                        Category.FLEXIBILITY, Intensity.MODERATE, 50, 320,
                        "https://res.cloudinary.com/dutmn3xde/image/upload/v1776611887/catgory_fexibility_moderate_d32ttr.jpg",
                        marcus),
                createWorkout("SPIN VELOCITY", "High-energy indoor cycling with rhythm-based intervals",
                        Category.CARDIO, Intensity.HARD, 45, 600,
                        "https://res.cloudinary.com/dutmn3xde/image/upload/v1776611950/category_hard_spin_velocity_f1fker.jpg",
                        marcus)
        );

        activityRepository.saveAll(workouts);

        log.info("Seeded {} workouts into the database", workouts.size());

        // ═══════════════════════════════════════════════════
        // 4. Generar historial de WorkoutLogs para John Drake
        //    Simula 3 meses de entrenamiento (~87 workouts)
        // ═══════════════════════════════════════════════════
        List<WorkoutLog> history = generateWorkoutHistory(demoUser, workouts, 90);
        workoutLogRepository.saveAll(history);

        log.info("Seeded {} historical workout logs for {}",
                history.size(), demoUser.getName());

        log.info("DataLoader finished successfully.");
    }

    /**
     * Creates an Activity with a random future date (1-30 days from now).
     */
    private Activity createWorkout(String title, String description, Category category,
                                   Intensity intensity, int durationMinutes, double calories,
                                   String imageUrl, Professor professor) {
        Activity activity = new Activity();
        activity.setTitle(title);
        activity.setDescription(description);
        activity.setCategory(category);
        activity.setIntensity(intensity);
        activity.setDurationMinutes(durationMinutes);
        activity.setCaloriesEstimate(calories);
        activity.setImageUrl(imageUrl);
        activity.setProfessor(professor);
        activity.setPrice(0.0);
        activity.setDate(LocalDateTime.now().plusDays(random.nextInt(30) + 1));
        return activity;
    }

    /**
     * Generates a realistic workout history simulating ~1 workout per day
     * (with some rest days) for the past N days.
     *
     * Each log:
     * - Picks a random activity from the catalog
     * - Adds slight variations to calories (+/- 15%) and duration (+/- 10 min)
     *   to simulate real-world variability
     * - Skips ~20% of days (rest days)
     */
    private List<WorkoutLog> generateWorkoutHistory(User user, List<Activity> activities, int daysBack) {
        List<WorkoutLog> logs = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (int dayOffset = daysBack; dayOffset >= 1; dayOffset--) {
            // Skip ~20% of days as rest days
            if (random.nextDouble() < 0.2) {
                continue;
            }

            Activity activity = activities.get(random.nextInt(activities.size()));

            // Variability: calories +/- 15%, duration +/- 10 min
            double calorieVariation = 0.85 + (random.nextDouble() * 0.3);
            double caloriesBurned = Math.round(activity.getCaloriesEstimate() * calorieVariation);
            int durationActual = activity.getDurationMinutes() + (random.nextInt(21) - 10);
            durationActual = Math.max(10, durationActual); // mínimo 10 min

            // Random hour between 6:00 and 21:00
            int hour = 6 + random.nextInt(16);
            int minute = random.nextInt(60);
            LocalDateTime completedAt = now.minusDays(dayOffset)
                    .withHour(hour)
                    .withMinute(minute)
                    .withSecond(0)
                    .withNano(0);

            WorkoutLog log = new WorkoutLog();
            log.setUser(user);
            log.setActivity(activity);
            log.setCompletedAt(completedAt);
            log.setCaloriesBurned(caloriesBurned);
            log.setDurationActualMinutes(durationActual);
            log.setNotes(null);

            logs.add(log);
        }

        return logs;
    }
}