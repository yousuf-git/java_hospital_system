package com.project.hospital.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "appointments")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"docId", "app_date", "app_time"})
})
public class Appointment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appId;

    @Column(nullable = false, length = 250)
    private String appRoom;

    @Column(name = "app_date", nullable = false)
    private LocalDate appDate;

    @Column(name = "app_time" ,nullable = false)
    private LocalTime appTime;

    @Column(length = 250)
    private String appReason;

    @Column(length = 10)
    private String appStatus;

    @ManyToOne
    @JoinColumn(name = "patId", nullable = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "docId", nullable = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Doctor doctor;
}

