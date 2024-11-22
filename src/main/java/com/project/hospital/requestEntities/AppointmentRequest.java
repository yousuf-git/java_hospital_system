package com.project.hospital.requestEntities;

import java.time.LocalDate;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AppointmentRequest {
    
    private String appRoom;
    private LocalDate appDate;
    private String appTime;
    private String appReason;
    private String appStatus;
    private int patId;
    private int docId;
}
