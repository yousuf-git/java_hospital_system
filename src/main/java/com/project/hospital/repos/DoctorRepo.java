package com.project.hospital.repos;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.hospital.entities.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {

    @Query ("SELECT d FROM doctors d WHERE d.docEmail = :email AND d.docPassword = :password")
    Doctor findByEmailAndPassword(@Param ("email") String email, @Param ("password") String password);

    
    @Query(
        // Doctor has schedule, Join docotrs with schedules and get schedule by doctor id
        "SELECT s FROM doctors d JOIN d.schedule s WHERE d.docId = :doctorId"
    )
    Object getScheduleByDoctor(@Param("doctorId") int doctorId);
    
}

// Dev Stuff
// hql query: "SELECT s FROM doctors d JOIN d.schedule s WHERE d.docId = :doctorId"
// sql query: "SELECT s FROM doctors d JOIN schedules s ON d.docId = s.docId WHERE d.docId = :doctorId"