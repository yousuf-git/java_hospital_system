package com.project.hospital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospital.entities.Admin;
import com.project.hospital.repos.AdminRepo;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;

    public List<Admin> getAllAdmins() {
        return adminRepo.findAll();
    }

    public Admin getAdminById(int id) {
        return adminRepo.findById(id).orElse(null);
    }

    public boolean addAdmin(Admin admin) {
        // if admin already exists
        if(adminRepo.existsById(admin.getAdminId())) {
            return false;
        }
        adminRepo.save(admin);
        return true;
    }

    // Returns previous admin object
    public Admin updateAdmin(int id, Admin admin) {
        // First check if the admin with the given id exists
        Admin previousAdmin = adminRepo.findById(id).orElse(null);
        if(previousAdmin == null) {
            return null;
        }
        // With the given id there is already an admin in database so to update the admin we need to set the id of the admin object to the given id and then save it so that the existing admin is updated
        admin.setAdminId(id);
        adminRepo.save(admin);
        return previousAdmin;
    }

    public Admin deleteAdmin(int id) {
        // First check if the admin with the given id exists
        Admin admin = adminRepo.findById(id).orElse(null);
        if(admin != null) {
            // If the admin exists then get the admin object and delete it
            adminRepo.deleteById(id);
            return admin;
        }
        return null;
    }

    public Admin loginAdmin(String email, String password) {
        return adminRepo.findByEmailAndPassword(email, password);
    }

}
