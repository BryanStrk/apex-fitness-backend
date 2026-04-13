package com.maspower.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "professors")
@Data
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(name = "hiring_year", nullable = false)
    private int hiringYear;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "image_url")
    private String imageUrl;
}