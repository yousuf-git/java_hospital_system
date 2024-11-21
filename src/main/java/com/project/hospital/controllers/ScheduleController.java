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

import com.project.hospital.entities.Schedule;
import com.project.hospital.services.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    // Service
    @Autowired
    private ScheduleService scheduleService;

    /*
     * Endpoints:
     * 
     * GET /schedule: Get all schedules.
     * GET /schedule/{id}: Get details of a specific schedule.
     * POST /schedule: Add a new schedule.
     * PUT /schedule/{id}: Update an existing schedule's details.
     * DELETE /schedule/{id}: Delete a schedule.
     * 
     *  Note: Do basic validation of JSON data received (if any) and Return responseEntity with appropriate status code.
     */

    // GET /schedules
    @GetMapping("/")
    public List<Schedule> getSchedules() {
        return scheduleService.getAllSchedules();
    }

    // GET /schedules/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getScheduleById(@PathVariable int id) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        Schedule schedule = scheduleService.getScheduleById(id);
        if(schedule == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(schedule);
        
    }

    // POST /schedules
    @PostMapping("/")
    public ResponseEntity<?> addSchedule(@RequestBody Schedule schedule) {
        // Validate schedule
        if(schedule == null) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid schedule");
        }
        // Add schedule
        scheduleService.addSchedule(schedule);
        // Return responseEntity with status code 201 which means created
        return ResponseEntity.status(201).body(schedule);
    }

    // PUT /schedules/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable int id, @RequestBody Schedule schedule) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        // Validate schedule
        if(schedule == null) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid schedule");
        }
        // Update schedule
        scheduleService.updateSchedule(id, schedule);
        // Return responseEntity with status code 200 which means success
        return ResponseEntity.ok(schedule);
    }

    // DELETE /schedules/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable int id) {
        // Validate id
        if(id < 0) {
            // Return responseEntity with status code 400 which means bad request
            return ResponseEntity.badRequest().body("Invalid id");
        }
        Schedule deletedSch = scheduleService.deleteSchedule(id);
        if (deletedSch == null) {
            // Return responseEntity with status code 404 which means not found
            return ResponseEntity.notFound().build();
        }
        // Return responseEntity with deleted schedule and status code 200 which means success
        return ResponseEntity.ok(deletedSch);
    }

}