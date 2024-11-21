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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospital.entities.Admin;
import com.project.hospital.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    // Service
    @Autowired
    private AdminService adminService;

    /*
     * Endpoints:
     * 
     * GET /admin: Get all admins.
     * GET /admin/{id}: Get details of a specific admin.
     * POST /admin: Add a new admin.
     * PUT /admin/{id}: Update an existing admin's details.
     * DELETE /admin/{id}: Delete an admin.
     * 
     *  Note: Do basic validation of JSON data received (if any) and Return responseEntity with appropriate status code.
     */

    // GET /admin
    @GetMapping("/")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    // GET /admin/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable int id) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        Admin admin = adminService.getAdminById(id);
        if(admin == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(admin);
        
    }

    // POST /admin
    @PostMapping("/")
    public ResponseEntity<?> addAdmin(@RequestBody Admin admin) {
        // Validate admin
        if(admin == null) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid admin");
        }
        adminService.addAdmin(admin);
        // Return responseEntity with status code 201 which means created
        return ResponseEntity.status(201).body(admin);
    }

    // PUT /admin/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable int id, @RequestBody Admin admin) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        // Validate admin
        if(admin == null) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid admin");
        }
        Admin prevAdmin = adminService.updateAdmin(id, admin);
        if(prevAdmin == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(admin);
    }


    // DELETE /admin/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable int id) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        Admin prevAdmin = adminService.deleteAdmin(id);
        if (prevAdmin == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(prevAdmin);
    }
}
