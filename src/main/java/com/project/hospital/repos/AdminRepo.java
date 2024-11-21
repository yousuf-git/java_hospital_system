package com.project.hospital.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.hospital.entities.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {

    @Query("SELECT a FROM admins a WHERE a.adminEmail = :em AND a.adminPassword = :psw")
    Admin findByEmailAndPassword(@Param("em") String email, @Param("psw") String password);

}
