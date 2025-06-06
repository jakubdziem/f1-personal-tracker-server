package com.dziem.f1_personal_tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    LocalDate time;
    @ManyToOne
    Race race;
    Integer rainfall;
    Float trackTemperature;
    Float airTemperature;
    Float windSpeed;
    Integer lapCount;
    Integer humidity;
}
