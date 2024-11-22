package com.project.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospital.entities.Admin;
import com.project.hospital.entities.Doctor;
import com.project.hospital.entities.Patient;
import com.project.hospital.requestEntities.AuthRequest;
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

    // GET /auth/admin
    @GetMapping("/admin")
    public ResponseEntity<?> loginAdmin(@RequestBody AuthRequest authRequest) {
        // Validate email and password
        if(authRequest.getEmail() == null || authRequest.getPassword() == null || authRequest.getEmail().isEmpty() || authRequest.getPassword().isEmpty()) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
        // Check if email and password are valid
        Admin admin = adminService.loginAdmin(authRequest.getEmail(), authRequest.getPassword());
        if(admin == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(admin);

    }

    // GET /auth/doctor
    @GetMapping("/doctor")
    public ResponseEntity<?> loginDoctor(@RequestBody AuthRequest authRequest) {
        // Validate email and password
        if(authRequest.getEmail() == null || authRequest.getPassword() == null || authRequest.getEmail().isEmpty() || authRequest.getPassword().isEmpty()) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
        // Check if email and password are valid
        Doctor doctor = doctorService.loginDoctor(authRequest.getEmail(), authRequest.getPassword());
        if(doctor == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(doctor);
    }

    // GET /auth/patient
    @GetMapping("/patient")
    public ResponseEntity<?> loginPatient(@RequestBody AuthRequest authRequest) {
        // Validate email and password
        if(authRequest.getEmail() == null || authRequest.getPassword() == null || authRequest.getEmail().isEmpty() || authRequest.getPassword().isEmpty()) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
        // Check if email and password are valid
        Patient patient = patientService.loginPatient(authRequest.getEmail(), authRequest.getPassword());
        if(patient == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(patient);
    }

    // POST /auth/patient

}
