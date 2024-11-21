package com.project.hospital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospital.entities.Doctor;
import com.project.hospital.repos.DoctorRepo;

@Service
public class DoctorService {

    // Repo
    @Autowired
    private DoctorRepo doctorRepo;

    // Add doctor
    public boolean addDoctor(Doctor doctor) {
        // If doctor already exists
        if(doctorRepo.existsById(doctor.getDocId())) {
            return false;
        }
        doctorRepo.save(doctor);
        return true;
    }

    // Update doctor
    public Doctor updateDoctor(int id, Doctor doctor) {
        // If doctor does not exist
        if(!doctorRepo.existsById(id)) {
            return null;
        }
        // With the given id there is already a doctor in database so to update the doctor we need to set the id of the doctor object to the given id and then save it so that the existing doctor is updated
        doctor.setDocId(id);
        doctorRepo.save(doctor);
        return doctor;
    }

    // Get doctor by id
    public Doctor getDoctorById(int id) {
        return doctorRepo.findById(id).orElse(null);
    }

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    // Delete doctor
    public Doctor deleteDoctor(int id) {
        // If doctor does not exist
        if(!doctorRepo.existsById(id)) {
            return null;
        }
        Doctor doctor = doctorRepo.findById(id).get();
        doctorRepo.deleteById(id);
        return doctor;
    }

    public Doctor loginDoctor(String email, String password) {
        return doctorRepo.findByEmailAndPassword(email, password);
    }

    public Object getScheduleByDoctor(int docId) {
        return doctorRepo.getScheduleByDoctor(docId);
    }

}
