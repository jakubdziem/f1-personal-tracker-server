package com.dziem.f1_personal_tracker.dtos;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class WeatherDTO {
    Integer id;
    LocalDate time;
    RaceDTO raceDTO;
    Integer rainfall;
    BigDecimal trackTemperature;
    BigDecimal airTemperature;
    BigDecimal windSpeed;
    Integer lapCount;
    Integer humidity;
}
