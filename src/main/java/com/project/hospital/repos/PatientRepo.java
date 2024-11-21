package com.project.hospital.repos;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.hospital.entities.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {

    @Query("SELECT p FROM patients p WHERE p.patEmail = :email AND p.patPassword = :password")
    Patient findByEmailAndPassword(@Param ("email") String email, @Param ("password") String password);
}
