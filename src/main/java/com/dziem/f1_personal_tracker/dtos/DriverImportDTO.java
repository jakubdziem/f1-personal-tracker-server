package com.dziem.f1_personal_tracker.dtos;

import lombok.Data;

import java.util.List;

@Data

public class DriverImportDTO {
    private String name;
    private List<RaceImportDTO> raceList;
    private List<ResultImportDTO> resultList;
    private List<LapTimeImportDTO> lapTimeList;
}
