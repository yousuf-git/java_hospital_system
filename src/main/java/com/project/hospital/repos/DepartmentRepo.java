package com.project.hospital.repos;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.hospital.entities.Department;
import com.project.hospital.entities.Doctor;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer> {

    // JOIN doctors d ON d.department.depId = :departId
    @Query("SELECT d FROM doctors d WHERE d.department.depId = :departId")
    List<Doctor> findDoctorsByDepartment(@Param("departId") int departId);
    
}
