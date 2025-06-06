package com.dziem.f1_personal_tracker.dtos;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class WeatherDTO {
    LocalDate time;
    RaceDTO raceDTO;
    Integer rainfall;
    Float trackTemperature;
    Float airTemperature;
    Float windSpeed;
    Integer lapCount;
    Integer humidity;
}
