package com.dziem.f1_personal_tracker.dtos;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class WeatherDTO {
    LocalDate time;
    RaceDTO raceDTO;
    Integer rainfall;
    BigDecimal trackTemperature;
    BigDecimal airTemperature;
    BigDecimal windSpeed;
    Integer lapCount;
    Integer humidity;
}
