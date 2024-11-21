package com.project.hospital.requestEntities;

import java.time.LocalDate;
import java.time.LocalTime;


@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AppointmentRequest {
    
    private int appId;
    private String appRoom;
    private LocalDate appDate;
    private String appTime;
    private String appReason;
    private String appStatus;
    private int patId;
    private int docId;
}
