package com.maspower.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "professors")
@Data
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Dni is required")
    @Column(nullable = false, unique = true)
    private String dni;

    @Min(value = 2000, message = "Hiring year must be 2000 or later")
    @Column(name = "hiring_year", nullable = false)
    private int hiringYear;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "image_url")
    private String imageUrl;
}