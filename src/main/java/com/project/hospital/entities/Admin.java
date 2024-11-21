package com.project.hospital.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "admins")
public class Admin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    @Column(nullable = false, length = 100)
    private String adminName;

    @Column(nullable = false, unique = true, length = 20)
    private Long adminPhone;

    @Column(nullable = false, unique = true, length = 100)
    private String adminEmail;

    @Column(nullable = false)
    private String adminPassword;

    // @Column(nullable = false)
    // private String role; // e.g., ADMIN, PATIENT
}

