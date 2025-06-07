package com.dziem.f1_personal_tracker.dtos;

import lombok.Data;

@Data
public class LapTimeImportDTO {
    private Integer lapNumber;
    private Integer milliseconds;
    private String raceName;
}
