package com.project.hospital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospital.entities.Department;
import com.project.hospital.repos.DepartmentRepo;

@Service
public class DepartService {

    // Repo
    @Autowired
    private DepartmentRepo departRepo;


    // Get all departments
    public List<Department> getAllDepartments() {
        return departRepo.findAll();
    }

    // Get department by id
    public Department getDepartmentById(int id) {
        return departRepo.findById(id).orElse(null);
    }

    // Add department
    public boolean addDepartment(Department department) {
        // If department already exists
        if(departRepo.existsById(department.getDepId())) {
            return false;
        }
        departRepo.save(department);
        return true;
    }

    // Update department
    public boolean updateDepartment(int id, Department department) {
        // If department does not exist
        if(!departRepo.existsById(id)) {
            return false;
        }
        department.setDepId(id);
        departRepo.save(department);
        return true;
    }

    // Delete department
    public Department deleteDepartment(int id) {
        // If department does not exist
        if(!departRepo.existsById(id)) {
            return null;
        }
        Department department = departRepo.findById(id).get();
        departRepo.deleteById(id);
        return department;
    }

}
