package com.dziem.f1_personal_tracker.dtos;

import lombok.Data;

import java.util.List;
@Data
public class RaceDTO {
    String name;
    Integer lapsQuantity;
    List<DriverDTO> driverList;
    List<WeatherDTO> weatherList;
}
