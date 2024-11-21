package com.project.hospital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospital.entities.Payment;
import com.project.hospital.repos.PaymentRepo;

@Service
public class PaymentService {

    @Autowired 
    private PaymentRepo paymentRepo;

    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }

    public Payment getPaymentById(int id) {
        return paymentRepo.findById(id).orElse(null);
    }

    public boolean addPayment(Payment payment) {
        if(paymentRepo.existsById(payment.getPayId())) {
            return false;
        }
        paymentRepo.save(payment);
        return true;
    }

    public Payment updatePayment(int id, Payment payment) {
        if(!paymentRepo.existsById(id)) {
            return null;
        }
        payment.setPayId(id);
        paymentRepo.save(payment);
        return payment;
    }

    public Payment deletePayment(int id) {
        if(!paymentRepo.existsById(id)) {
            return null;
        }
        Payment payment = paymentRepo.findById(id).get();
        paymentRepo.deleteById(id);
        return payment;
    }

}
