package com.project.hospital.repos;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.hospital.entities.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer> {
    
}
