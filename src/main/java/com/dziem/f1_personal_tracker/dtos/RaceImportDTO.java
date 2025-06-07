package com.dziem.f1_personal_tracker.dtos;

import lombok.Data;

@Data
public class RaceImportDTO {
    private String name;
    private int year;
    private Integer lapsQuantity;
}
