package com.project.hospital.controllers;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospital.entities.Appointment;
import com.project.hospital.entities.Doctor;
import com.project.hospital.entities.Patient;
import com.project.hospital.repos.DoctorRepo;
import com.project.hospital.repos.PatientRepo;
import com.project.hospital.requestEntities.AppointmentRequest;
import com.project.hospital.services.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    // Service
    @Autowired
    private AppointmentService appointmentService;

    /*
     * Endpoints:
     * 
     * GET /appointment: Get all appointments.
     * GET /appointment/{id}: Get details of a specific appointment.
     * POST /appointment: Add a new appointment.
     * PUT /appointment/{id}: Update an existing appointment's details.
     * DELETE /appointment/{id}: Delete an appointment.
     * 
     *  Note: Do basic validation of JSON data received (if any) and Return responseEntity with appropriate status code.
     */

    // GET /appointment
    @GetMapping("/")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // GET /appointment/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable int id) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        Appointment appointment = appointmentService.getAppointmentById(id);
        if(appointment == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(appointment);
        
    }

    // POST /appointment -> Requires more data from front-end so used AppointmentRequest class below instead
    // @PostMapping("/")
    // public ResponseEntity<?> addAppointment(@RequestBody Appointment appointment) {
    //     // Validate appointment
    //     if(appointment == null) {
    //         // Return responseEntity with status code 400 which means bad request
    //         return ResponseEntity.badRequest().body("Invalid appointment");
    //     }
    //     // If appointment already exists:
    //     if (appointmentService.getAppointmentById(appointment.getAppId()) != null) {
    //         // Return responseEntity with status code 400 which means bad request
    //         return ResponseEntity.badRequest().body("Appointment already exists");
    //     }

    //     if (appointmentService.addAppointment(appointment)) {
    //         // Return responseEntity with status code 201 which means created
    //         return ResponseEntity.status(201).body(appointment);
    //     }
    //     // ELSE The doctor is not available at that time
    //     return ResponseEntity.badRequest().body("Doctor is not available at that time");
    // }

    @Autowired
    private PatientRepo patientRepo;
    
    @Autowired
    private DoctorRepo doctorRepo;
    
    @PostMapping("/appointments")
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        Patient patient = patientRepo.findById(appointmentRequest.getPatId()).orElse(null);
        Doctor doctor = doctorRepo.findById(appointmentRequest.getDocId()).orElse(null);
        if (patient == null || doctor == null) {
            return ResponseEntity.badRequest().body("Patient or Doctor does not exist");
        }
        // Convert the appTime string to LocalTime
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime appTime = LocalTime.parse(appointmentRequest.getAppTime(), timeFormatter);

        Appointment appointment = Appointment.builder()
            .appRoom(appointmentRequest.getAppRoom())
            .appDate(appointmentRequest.getAppDate())
            .appTime(appTime)
            .appReason(appointmentRequest.getAppReason())
            .appStatus(appointmentRequest.getAppStatus())
            .patient(patient)
            .doctor(doctor)
            .build();
        if (appointmentService.addAppointment(appointment)) {
            return ResponseEntity.ok(appointment);
        }
        return ResponseEntity.badRequest().body("Doctor is not available at that time");
    }

    // PUT /appointment/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable int id, @RequestBody Appointment appointment) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        if (appointmentService.updateAppointment(id, appointment)) {
            // Return responseEntity with status code 200 which means success
            return ResponseEntity.ok(appointment);
        }
        return ResponseEntity.badRequest().body("Appointment does not exist"); // code 400
    }

    // DELETE /appointment/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable int id) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        Appointment delApt = appointmentService.deleteAppointment(id);
        if (delApt != null) {
            // Return responseEntity with status code 200 which means success
            return ResponseEntity.ok("Appointment deleted successfully");
        }
        // code not found and return deleted appointment
        return ResponseEntity.notFound().build();
    }

}
