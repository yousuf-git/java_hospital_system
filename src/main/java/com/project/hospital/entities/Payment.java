package com.project.hospital.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payId;

    @Column(nullable = false, length = 250)
    private String payMethod;

    @Column(nullable = false)
    // TimeStamp
    private LocalDateTime payDateTime;

    @Column(nullable = false)
    private Float payAmount;

    @ManyToOne
    @JoinColumn(name = "patId", nullable = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    // Join column means that the column in the Payment table that will be used to join with the Patient table is patId
    private Patient patient;
}

/* 
Its JSON representation:
{
    "payId": 1,
    "payMethod": "Cash",
    "payDate": "2021-12-01",
    "payAmount": 100.0,
    "patient": {
        "patId": 1,
        "patName": "John Doe",
        "patDob": "1990-01-01",
        "patGender": "M"
    }
}

*/ 



