package com.project.hospital.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospital.entities.Payment;
import com.project.hospital.services.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    
    // Service
    @Autowired
    private PaymentService paymentService;

    /*
     * Endpoints:
     * 
        * GET /payment: Get all patients.
        * GET /payment/{id}: Get details of a specific patient.
        * POST /payment: Add a new patient.
        * PUT /payment/{id}: Update an existing patient's details.
        * DELETE /payment/{id}: Delete a patient.

        *  Note: Do basic validation of JSON data received (if any) and Return responseEntity with appropriate status code.
    */

    // GET /payment
    @GetMapping("/")
    public List<Payment> getPayments() {
        return paymentService.getAllPayments();
    }

    // GET /payment/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable int id) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        Payment payment = paymentService.getPaymentById(id);
        if(payment == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(payment);
        
    }

    // POST /payment
    @PostMapping("/")
    public ResponseEntity<?> addPayment(@RequestBody Payment payment) {
        // Validate payment
        if(payment == null) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid payment");
        }
        if(paymentService.addPayment(payment)) {
            // Return responseEntity with status code 201 which means created
            return ResponseEntity.status(201).body(payment);
        }
        // Return responseEntity with status code 400 which means bad request
        return ResponseEntity.badRequest().body("Payment already exists");
    }

    // PUT /payment/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable int id, @RequestBody Payment payment) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        // Validate payment
        if(payment == null) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid payment");
        }
        Payment updatedPayment = paymentService.updatePayment(id, payment);
        if(updatedPayment == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(updatedPayment);
    }

    // // DELETE /payment/{id}
    // @DeleteMapping("/{id}")
    // public ResponseEntity<?> deletePayment(@PathVariable int id) {
    //     // Validate id
    //     if(id < 0) {
    //         // Return responseEntity with status code 400 which means bad request
    //         return ResponseEntity.badRequest().body("Invalid id");
    //     }
    //     if(paymentService.deletePayment(id)) {
    //         // Return responseEntity with status code 200 which means success
    //         return ResponseEntity.ok("Payment deleted successfully");
    //     }
    //     // Return responseEntity with status code 404 which means not found
    //     return ResponseEntity.notFound().build();
    // }




}