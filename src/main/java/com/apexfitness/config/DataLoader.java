package com.apexfitness.config;

import com.apexfitness.model.*;
import com.apexfitness.repository.ActivityRepository;
import com.apexfitness.repository.ProfessorRepository;
import com.apexfitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Seeds the database with initial workouts and a default coach.
 * Runs only if the database is empty.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final ActivityRepository activityRepository;
    private final ProfessorRepository professorRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        // Solo seedeamos si la BD está vacía
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
        // 2. Crear un usuario de prueba
        // ═══════════════════════════════════════════════════
        User demoUser = new User();
        demoUser.setName("John");
        demoUser.setSurname("Drake");
        demoUser.setDni("12345678Z");
        demoUser.setRegistrationYear(2024);
        demoUser.setActive(true);
        demoUser.setImageUrl(null);
        userRepository.save(demoUser);

        log.info("Created demo user: {} {}", demoUser.getName(), demoUser.getSurname());

        // ═══════════════════════════════════════════════════
        // 3. Crear los 8 workouts del mock del frontend
        // ═══════════════════════════════════════════════════
        List<Activity> workouts = List.of(
                createWorkout(
                        "METABOLIC BREAKDOWN",
                        "High-intensity interval training for maximum caloric burn",
                        Category.HIIT,
                        Intensity.EXTREME,
                        45,
                        650,
                        "https://res.cloudinary.com/dutmn3xde/image/upload/v1776594314/category_hiit_wpndxv.jpg",
                        marcus
                ),
                createWorkout(
                        "POWER YOGA FLOW",
                        "Build strength and flexibility through dynamic sequences",
                        Category.YOGA,
                        Intensity.MODERATE,
                        60,
                        380,
                        "https://res.cloudinary.com/dutmn3xde/image/upload/v1776611489/category_yoga_hwftfm.jpg",
                        marcus
                ),
                createWorkout(
                        "CROSSFIT BEAST MODE",
                        "Functional movements at high intensity",
                        Category.STRENGTH,
                        Intensity.EXTREME,
                        50,
                        720,
                        "https://res.cloudinary.com/dutmn3xde/image/upload/v1776611570/category_strength_nsba3a.jpg",
                        marcus
                ),
                createWorkout(
                        "CARDIO RUSH",
                        "Explosive cardio for endurance and stamina",
                        Category.CARDIO,
                        Intensity.HARD,
                        35,
                        520,
                        "https://res.cloudinary.com/dutmn3xde/image/upload/v1776611721/gategory_cardio_at9k5v.jpg",
                        marcus
                ),
                createWorkout(
                        "IRON FOUNDRY",
                        "Pure strength training with progressive overload",
                        Category.STRENGTH,
                        Intensity.HARD,
                        55,
                        480,
                        "https://res.cloudinary.com/dutmn3xde/image/upload/v1776611725/category_strenght_hard_ydduaa.jpg",
                        marcus
                ),
                createWorkout(
                        "COMBAT STRIKE",
                        "Boxing combinations and explosive power",
                        Category.COMBAT,
                        Intensity.HARD,
                        40,
                        580,
                        "https://res.cloudinary.com/dutmn3xde/image/upload/v1776611827/catgory_combat_hard_tgrgsb.jpg",
                        marcus
                ),
                createWorkout(
                        "PILATES PRECISION",
                        "Core-focused flexibility and controlled movements",
                        Category.FLEXIBILITY,
                        Intensity.MODERATE,
                        50,
                        320,
                        "https://res.cloudinary.com/dutmn3xde/image/upload/v1776611887/catgory_fexibility_moderate_d32ttr.jpg",
                        marcus
                ),
                createWorkout(
                        "SPIN VELOCITY",
                        "High-energy indoor cycling with rhythm-based intervals",
                        Category.CARDIO,
                        Intensity.HARD,
                        45,
                        600,
                        "https://res.cloudinary.com/dutmn3xde/image/upload/v1776611950/category_hard_spin_velocity_f1fker.jpg",
                        marcus
                )
        );

        activityRepository.saveAll(workouts);

        log.info("Seeded {} workouts into the database", workouts.size());
        log.info("DataLoader finished successfully.");
    }

    /**
     * Helper para crear una Activity con fecha futura aleatoria
     * (entre 1 y 30 días desde hoy).
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
        activity.setPrice(0.0); // gratis por defecto
        activity.setDate(LocalDateTime.now().plusDays((long) (Math.random() * 30) + 1));
        return activity;
    }
}