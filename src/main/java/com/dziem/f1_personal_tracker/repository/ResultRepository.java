package com.dziem.f1_personal_tracker.repository;

import com.dziem.f1_personal_tracker.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Integer> {
}
