package com.dziem.f1_personal_tracker.repository;

import com.dziem.f1_personal_tracker.model.LapTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LapTimeRepository extends JpaRepository<LapTime, Integer> {
}
