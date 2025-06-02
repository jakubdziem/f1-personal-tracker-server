package com.dziem.f1_personal_tracker.repository;

import com.dziem.f1_personal_tracker.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<Race, Integer> {
}
