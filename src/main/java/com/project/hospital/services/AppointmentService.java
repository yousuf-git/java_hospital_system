package com.project.hospital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.project.hospital.repos.AppointmentRepo;

import com.project.hospital.entities.Appointment;

@Service
public class AppointmentService {

    // Repo
    @Autowired
    private AppointmentRepo appointmentRepo;

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    // Get appointment by id
    public Appointment getAppointmentById(int id) {
        return appointmentRepo.findById(id).orElse(null);
    }

    // Add appointment
    public Appointment addAppointment(Appointment appointment) {
        // If appointment already exists
        // if(appointmentRepo.existsById(appointment.getAppId())) {
        //     return false;
        // }
        // Appointment has time and doctor, doctor has schedule, check if appointment time is in between start and end time of doctor's schedule and there isn't any other appointment at that time
        // if(appointmentRepo.isAppointmentTimeValid(appointment.getDoctor().getDocId(), appointment.getAppTime())) {
        //     appointmentRepo.save(appointment);
        //     return true;
        // }
        try {
            return appointmentRepo.save(appointment);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("This time slot is already booked for the selected doctor.");
        }

    }

    // Update appointment
    public boolean updateAppointment(int id, Appointment appointment) {
        // If appointment does not exist
        if(!appointmentRepo.existsById(id)) {
            return false;
        }
        appointment.setAppId(id);
        appointmentRepo.save(appointment);
        return true;
    }

    public Appointment deleteAppointment(int id) {
        // If appointment does not exist
        if(!appointmentRepo.existsById(id)) {
            return null;
        }
        Appointment appointment = appointmentRepo.findById(id).get();
        appointmentRepo.deleteById(id);
        return appointment;
    }

    public List<?> getPatientsByDoctor(int doctorId) {
        return appointmentRepo.findPatientsByDoctorWithCompletedAppointments(doctorId);
    }

    public List<?> getAppointmentsByDoctor(int doctorId) {
        return appointmentRepo.findAppointmentsByDoctor(doctorId);
    }

    public List<Appointment> getAppointmentsByPatient(int patId) {
        return appointmentRepo.findAppointmentsByPatient(patId);
    }

}
