package com.project.hospital.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "doctors")
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int docId;

    @Column(nullable = false, length = 250)
    private String docName;

    @Column(nullable = false, length = 15)
    private Long docPhone;

    @Column(nullable = false, length = 1)
    private String docGender;

    @Column(nullable = false)
    private Double docFees;

    // Email and password for login
    @Column(nullable = false, length = 250)
    private String docEmail;

    @Column(nullable = false, length = 250)
    private String docPassword;

    @ManyToOne
    @JoinColumn(name = "depId", nullable = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Department department;

    // @OneToOne
    // Many doctors can have same schedule so using ManyToOne
    @ManyToOne
    @JoinColumn(name = "schId")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Schedule schedule;
}

