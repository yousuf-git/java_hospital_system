package com.project.hospital.entities;

import java.time.LocalDate;

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
@Entity(name = "patients")
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patId;

    @Column(nullable = false, length = 250)
    private String patName;

    @Column(nullable = false)
    private LocalDate patDob;
    
    @Column(nullable = false, length = 15)
    // private Integer patPhone;
    // Max integer value that it can hold is 2147483647 which is 10 digits so using Long instead of Integer
    private Long patPhone; // Long is 19 digits
    
    @Column(nullable = false, length = 1)
    private String patGender;

    // Email
    @Column(nullable = false, length = 250)
    private String patEmail;

    // Password
    @Column(nullable = false, length = 250)
    private String patPassword;
}

