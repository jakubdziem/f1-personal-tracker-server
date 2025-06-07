package com.dziem.f1_personal_tracker.dtos;

import lombok.Data;

import java.time.Year;

@Data
public class RaceExportDTO {
    private String name;
    private Year year;
    private Integer lapsQuantity;
}