package com.dziem.f1_personal_tracker.repository;

import com.dziem.f1_personal_tracker.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
}
