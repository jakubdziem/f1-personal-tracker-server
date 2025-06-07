package com.dziem.f1_personal_tracker.dtos;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ResultExportDTO {
    private Integer position;
    private LocalTime raceTime;
    private String raceName;
}
