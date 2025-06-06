package com.dziem.f1_personal_tracker.repository;

import com.dziem.f1_personal_tracker.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RaceRepository extends JpaRepository<Race, Integer> {
    Optional<List<Race>> findRacesByName(String name);
    @Query("SELECT DISTINCT r FROM Race r LEFT JOIN FETCH r.weatherList")
    List<Race> findAllWithWeather();
}
