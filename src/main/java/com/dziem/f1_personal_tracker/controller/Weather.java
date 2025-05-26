package com.dziem.f1_personal_tracker.controller;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    LocalDate time;
    @ManyToOne
    Race race;
    Integer rainfall;
    BigDecimal trackTemperature;
    BigDecimal airTemperature;
    BigDecimal windSpeed;
    Integer lapCount;
    Integer humidity;
}
