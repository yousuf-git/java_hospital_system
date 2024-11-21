package com.project.hospital.repos;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.hospital.entities.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {
}
