package com.project.hospital.repos;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.hospital.entities.Schedule;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {
}
