package com.dziem.f1_personal_tracker.repository;

import com.dziem.f1_personal_tracker.model.Driver;
import com.dziem.f1_personal_tracker.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Integer> {
    @Query("SELECT r FROM Result r WHERE r.race.year IN :years")
    List<Result> findByRaceYearIn(@Param("years") List<java.time.Year> years);

    List<Result> findAllByDriver(Driver driver);
}
