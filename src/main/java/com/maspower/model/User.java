package com.maspower.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(name = "registration_year", nullable = false)
    private int registrationYear;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "image_url")
    private String imageUrl;
}