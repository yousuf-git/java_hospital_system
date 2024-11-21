package com.project.hospital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospital.entities.Schedule;
import com.project.hospital.repos.ScheduleRepo;

@Service
public class ScheduleService {

    // Repo
    @Autowired
    private ScheduleRepo scheduleRepo;

    // Get schedule by id
    public Schedule getScheduleById(int id) {
        return scheduleRepo.findById(id).orElse(null);
    }

    // Get all schedules
    public List<Schedule> getAllSchedules() {
        return scheduleRepo.findAll();
    }

    // Add schedule
    public void addSchedule(Schedule schedule) {
        scheduleRepo.save(schedule);
    }

    // Update schedule
    public void updateSchedule(int id, Schedule schedule) {
        schedule.setSchId(id);
        scheduleRepo.save(schedule);
    }

    // Delete schedule
    public Schedule deleteSchedule(int id) {
        // If schedule does not exist
        if (!scheduleRepo.existsById(id)) {
            return null;
        }

        Schedule schedule = scheduleRepo.findById(id).get();
        scheduleRepo.deleteById(id);
        return schedule;
    }

}
