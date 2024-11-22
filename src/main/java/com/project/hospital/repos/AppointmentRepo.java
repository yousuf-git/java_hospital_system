package com.project.hospital.repos;

import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.hospital.entities.Appointment;
import com.project.hospital.entities.Patient;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

    @Query("SELECT DISTINCT p FROM appointments a JOIN a.patient p WHERE a.doctor.docId = :doctorId AND a.appStatus = 'completed'")
    List<Patient> findPatientsByDoctorWithCompletedAppointments(@Param("doctorId") int doctorId);

    @Query("SELECT a FROM appointments a WHERE a.doctor.docId = :doctorId")
    List<?> findAppointmentsByDoctor(int doctorId);

    @Query("""
            SELECT CASE WHEN COUNT(a) = 0 THEN true ELSE false END
            FROM appointments a
            JOIN doctors d ON a.doctor.docId = d.docId
            JOIN d.schedule s
            WHERE d.docId = :doctorId
            AND a.appStatus = 'pending' 
            AND :appTime BETWEEN s.schFrom AND s.schTo
            AND (a.appTime = :appTime)
        """)
    boolean isAppointmentTimeValid(@Param("doctorId") int doctorId, @Param("appTime") LocalTime appointmentTime);

    @Query("SELECT a FROM appointments a WHERE a.patient.patId = :patId")
    List<Appointment> findAppointmentsByPatient(@Param("patId") int patientId);

    /*
     * Response JSON:
     * 
     */
}
