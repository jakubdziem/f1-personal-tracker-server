package com.dziem.f1_personal_tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer position;
    LocalTime raceTime;
    @ManyToOne
    Driver driver;
    @ManyToOne
    @JoinColumn(name = "race_id")
    Race race;
}
