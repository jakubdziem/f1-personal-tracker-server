package com.dziem.f1_personal_tracker.dtos;

import lombok.Data;

@Data
public class TemperatureLapTimeDTO {
    Float trackTemperature;
    Float avgLapTimeMs;
}
