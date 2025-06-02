package com.dziem.f1_personal_tracker.repository;

import com.dziem.f1_personal_tracker.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
}
