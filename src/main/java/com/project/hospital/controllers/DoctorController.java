package com.project.hospital.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospital.entities.Doctor;
import com.project.hospital.services.AppointmentService;
import com.project.hospital.services.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    // Service
    @Autowired
    private DoctorService doctorService;

    /*
     * Endpoints:
     * 
     * GET /doctor: Get all doctors.
     * GET /doctor/{id}: Get details of a specific doctor.
     * POST /doctor: Add a new doctor.
     * PUT /doctor/{id}: Update an existing doctor's details.
     * DELETE /doctor/{id}: Delete a doctor.
     * 
     *  Note: Do basic validation of JSON data received (if any) and Return responseEntity with appropriate status code.
     */

    // GET /doctor
    @GetMapping("/")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // GET /doctor/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable int id) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        Doctor doctor = doctorService.getDoctorById(id);
        if(doctor == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(doctor);
        
    }

    // POST /doctor
    @PostMapping("/")
    public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor) {
        // Validate doctor
        if(doctor == null) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid doctor");
        }
        // If doctor already exists
        if(!doctorService.addDoctor(doctor)) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Doctor already exists");
        }
        // Return responseEntity with status code 201 which means created
        return ResponseEntity.status(201).body(doctor);
    }

    // PUT /doctor/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable int id, @RequestBody Doctor doctor) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        // Validate doctor
        if(doctor == null) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid doctor");
        }
        Doctor updatedDoctor = doctorService.updateDoctor(id, doctor);
        if(updatedDoctor == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(updatedDoctor);
    }

    // DELETE /doctor/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable int id) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        Doctor doctor = doctorService.getDoctorById(id);
        if(doctor == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return the deleted doctor and status code 200 which means success
        return ResponseEntity.ok(doctorService.deleteDoctor(id));
    }

    @Autowired
    private AppointmentService appointmentService;

    // GET /doctor/patients
    @GetMapping("/patients")
    public ResponseEntity<?> getCompletedAppointmentsPatients(@RequestAttribute("doctorId") int doctorId) {
        List<?> patients = appointmentService.getPatientsByDoctor(doctorId);
        return ResponseEntity.ok(patients);
    }
    /*
        JS fetch request for above controller:
        fetch('/doctor/patients', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                '
            },
            body: JSON.stringify({
                doctorId: 1
            })
        })
     */

    // GET /doctor/appointments
    @GetMapping("/appointments")
    public ResponseEntity<?> getCompletedAppointments(@RequestParam int docId) {
        List<?> appointments = appointmentService.getAppointmentsByDoctor(docId);
        return ResponseEntity.ok(appointments);
    }

    // Fetch request for above controller:
    // fetch('/doctor/appointments', {
    //     method: 'GET',
    //     headers: {
    //         'Content-Type': 'application/json',
    //     },
    //     body: JSON.stringify({
    //         docId: 1
    //     })
    // })

    // Get schedule by docId
    // GET /doctor/schedule
    @GetMapping("/schedule")
    public ResponseEntity<?> getScheduleByDoctor(@RequestAttribute("docId") int docId) {
        return ResponseEntity.ok(doctorService.getScheduleByDoctor(docId));
    }


    // If doctor changes status of appointment -> Currently using PUT /appointment/{id} from AppointmentController
    // // PUT /doctor/appointment/{id}
    // @PutMapping("/appointment/{id}")
    // public ResponseEntity<?> updateAppointmentStatus(@RequestAttribute("doctorId") int doctorId, @PathVariable int id, @RequestBody String status) {
    //     if(status == null || status.isEmpty()) {
    //         return ResponseEntity.badRequest().body("Invalid status");
    //     }
    //     if(!status.equals("completed") && !status.equals("cancelled")) {
    //         return ResponseEntity.badRequest().body("Invalid status");
    //     }
    //     if(appointmentService.updateAppointmentStatus(doctorId, id, status)) {
    //         return ResponseEntity.ok("Status updated");
    //     }
    //     return ResponseEntity.notFound().build();
    // }

}

/*
 * 
 * Developer's stuff:
 * 
 * Q: Diff between @Controller and @RestController?
 * A: @Controller is used to define a class as Spring Controller
 * while @RestController is a convenience annotation that does nothing more than
 * adding the @Controller and @ResponseBody annotations.
 * 
 * Q: When to use @Controller and @RestController?
 * A: Use @Controller when you want to return a view, and @RestController when
 * you want to return an object.
 * 
 */
