package com.project.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospital.entities.Admin;
import com.project.hospital.entities.Doctor;
import com.project.hospital.entities.Patient;
import com.project.hospital.services.AdminService;
import com.project.hospital.services.DoctorService;
import com.project.hospital.services.PatientService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // Whoever logins, sends email and password to this endpoint
    // All the relevant data of that user is returned and if it is not valid then suitable error code

    @Autowired
    private AdminService adminService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;

    // POST /auth/admin
    @PostMapping("/admin")
    public ResponseEntity<?> loginAdmin(@RequestBody String email, @RequestBody String password) {
        // Validate email and password
        if(email == null || password == null || email.isEmpty() || password.isEmpty()) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
        // Check if email and password are valid
        Admin admin = adminService.loginAdmin(email, password);
        if(admin == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(admin);

    }

    // POST /auth/doctor
    @PostMapping("/doctor")
    public ResponseEntity<?> loginDoctor(@RequestBody String email, @RequestBody String password) {
        // Validate email and password
        if(email == null || password == null || email.isEmpty() || password.isEmpty()) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
        // Check if email and password are valid
        Doctor doctor = doctorService.loginDoctor(email, password);
        if(doctor == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(doctor);
    }

    // POST /auth/patient
    @PostMapping("/patient")
    public ResponseEntity<?> loginPatient(@RequestBody String email, @RequestBody String password) {
        // Validate email and password
        if(email == null || password == null || email.isEmpty() || password.isEmpty()) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
        // Check if email and password are valid
        Patient patient = patientService.loginPatient(email, password);
        if(patient == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(patient);
    }

    // POST /auth/patient

}
