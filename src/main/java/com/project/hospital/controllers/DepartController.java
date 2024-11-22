package com.project.hospital.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospital.entities.Department;
import com.project.hospital.services.DepartService;

@RestController
public class DepartController {

    // Service
    @Autowired
    private DepartService departService;

    /*
     * Endpoints:
     * 
     * GET /department: Get all departments.
     * GET /department/{id}: Get details of a specific department.
     * POST /department: Add a new department.
     * PUT /department/{id}: Update an existing department's details.
     * DELETE /department/{id}: Delete a department.
     * 
     * Note: Do basic validation of JSON data received (if any) and Return responseEntity with appropriate status code.
     */

    // GET /departments
    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departService.getAllDepartments();
    }

    // GET /departments/{id}
    @GetMapping("/departments/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable int id) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        Department department = departService.getDepartmentById(id);
        if(department == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(department);
        
    }

    // POST /departments
    @PostMapping("/departments")
    public ResponseEntity<?> addDepartment(@RequestBody Department department) {
        // Validate department
        if(department == null) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid department");
        }
        if (departService.addDepartment(department)) {
            // Return responseEntity with status code 201 which means created
            return ResponseEntity.status(201).body(department);
        } else {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Department already exists");
        }
    }

    // PUT /departments/{id}
    @PutMapping("/departments/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable int id, @RequestBody Department department) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        if (departService.updateDepartment(id, department)) {
            // Return responseEntity with status code 200 which means success
            return ResponseEntity.ok(department);
        } else {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /departments/{id}
    @DeleteMapping("/departments/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable int id) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        Department deletedDepart = departService.deleteDepartment(id);
        // if not null return deleted department with status code 200
        if (deletedDepart != null) {
            return ResponseEntity.ok(deletedDepart);
        }
        // Return responseEntity with status code 404 which means not found
        return ResponseEntity.notFound().build();
    }

    // Get all doctors of a department
    @GetMapping("/doctors")
    public List<?> getDoctorsByDepartment(@RequestHeader int departId) {
        return departService.getDoctorsByDepartment(departId);
    }

}
