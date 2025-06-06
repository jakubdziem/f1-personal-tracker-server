package com.dziem.f1_personal_tracker.dtos;

import lombok.Data;

@Data
public class DriverAveragePositionDTO {
    String driverName;
    Float drvAvgPosition;
    Float rainyAvgPosition;
}
