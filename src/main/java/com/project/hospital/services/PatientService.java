package com.project.hospital.services;

// import java.time.LocalDate;
import java.util.List;
// import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospital.entities.Appointment;
import com.project.hospital.entities.Patient;
import com.project.hospital.repos.PatientRepo;

@Service
public class PatientService {

    // Autowire the PatientRepo
    // @Autowired
    // private PatientRepo patientRepo;

    // Using Constructor injection for repo
    private final PatientRepo patientRepo;

    @Autowired
    public PatientService(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }
    
    // Get all patients
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }
    // public List<?> getAllPatients() {
    //     List<Patient> patients = patientRepo.findAll();
    //     // Map Patient entities to response DTOs
    //     return patients.stream()
    //             .map(patient -> new PatientResponse(
    //                     patient.getPatId(),
    //                     patient.getPatName(),
    //                     patient.getPatDob(),
    //                     patient.getPatGender()))
    //             .collect(Collectors.toList());
    // }
    // static class PatientResponse {
    //     private int patId;
    //     private String patName;
    //     private LocalDate patDOB;
    //     private String patGender;
        
    //     public PatientResponse(int patId, String patName, LocalDate patDOB, String patGender) {
    //         this.patId = patId;
    //         this.patName = patName;
    //         this.patDOB = patDOB;
    //         this.patGender = patGender;
    //     }
        
    //     public int getPatId() {
    //         return patId;
    //     }
        
    //     public String getPatName() {
    //         return patName;
    //     }
        
    //     public LocalDate getPatDOB() {
    //         return patDOB;
    //     }
        
    //     public String getPatGender() {
    //         return patGender;
    //     }
    // }

    // Get patient by id
    public Patient getPatientById(int id) {
        return patientRepo.findById(id).orElse(null);
    }

    // Add patient
    public boolean addPatient(Patient patient) {
        // If patient already exists
        if(patientRepo.existsById(patient.getPatId())) {
            return false;
        }
        patientRepo.save(patient);
        return true;
    }

    // Update patient
    public boolean updatePatient(int id, Patient patient) {
        // If patient does not exist
        if(!patientRepo.existsById(id)) {
            return false;
        }
        patient.setPatId(id);
        patientRepo.save(patient);
        return true;
    }

    // Delete patient
    public boolean deletePatient(int id) {
        // If patient does not exist
        if(!patientRepo.existsById(id)) {
            return false;
        }
        patientRepo.deleteById(id);
        return true;
    }

    public Patient loginPatient(String email, String password) {
        return patientRepo.findByEmailAndPassword(email, password);
    }

    @Autowired
    private AppointmentService appointmentService;

    public List<Appointment> getAppointments(int patId) {
        Patient patient = getPatientById(patId);
        if(patient == null) {
            return null;
        }
        return appointmentService.getAppointmentsByPatient(patient.getPatId());
    }

}
