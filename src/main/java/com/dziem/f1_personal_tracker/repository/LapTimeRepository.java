package com.dziem.f1_personal_tracker.repository;

import com.dziem.f1_personal_tracker.model.Driver;
import com.dziem.f1_personal_tracker.model.LapTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LapTimeRepository extends JpaRepository<LapTime, Integer> {
    List<LapTime> findAllByDriver(Driver driver);
}
