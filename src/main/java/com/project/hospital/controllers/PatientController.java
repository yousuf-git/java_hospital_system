package com.project.hospital.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospital.entities.Appointment;
import com.project.hospital.entities.Patient;
import com.project.hospital.services.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
    
    // Service
    @Autowired
    private PatientService patientService;

    /*
     * Endpoints:
     * 
     * GET /patient: Get all patients.
     * GET /patient/{id}: Get details of a specific patient.
     * POST /patient: Add a new patient.
     * PUT /patient/{id}: Update an existing patient's details.
     * DELETE /patient/{id}: Delete a patient.
     * 
     *  Note: Do basic validation of JSON data received (if any) and Return responseEntity with appropriate status code.
     */

    // GET /patient
    @GetMapping("/")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    // GET /patient/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable int id) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        Patient patient = patientService.getPatientById(id);
        if(patient == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(patient);
        
    }

    // POST /patient
    @PostMapping("/")
    public ResponseEntity<?> addPatient(@RequestBody Patient patient) {
        // Validate patient
        if(patient == null) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid patient");
        }
        if (patientService.addPatient(patient)) {
            // Return responseEntity with status code 201 which means created
            return ResponseEntity.status(201).body(patient);
        }
        return ResponseEntity.badRequest().body("Patient already exists"); // code 400
    }

    // PUT /patient/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable int id, @RequestBody Patient patient) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        // Validate patient
        if(patient == null) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid patient");
        }
        if (patientService.updatePatient(id, patient)) {
            // Return responseEntity with status code 200 which means success
            return ResponseEntity.ok(patient);
        }
        return ResponseEntity.badRequest().body("Patient not found"); // code 400
    }

    // DELETE /patient/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable int id) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        if (patientService.deletePatient(id)) {
            // Return responseEntity with status code 200 which means success
            return ResponseEntity.ok("Patient deleted successfully");
        }
        return ResponseEntity.badRequest().body("Patient not found"); // code 400
    }

    // Patient can see his appoitments
    @GetMapping("/appointments")
    public ResponseEntity<?> getAppointments(@RequestHeader("patId") int patId) {
        // Validate id
        if(patId < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        List<Appointment> appointments = patientService.getAppointments(patId);
        if(appointments == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // if (appointments.size() == 0) {
        //     return ResponseEntity.ok("No appointments found");
        // }            // Can be done on client side
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(appointments);
    }

}