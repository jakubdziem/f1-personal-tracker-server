package com.dziem.f1_personal_tracker.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DriverExportDTO {
    private String name;
    private List<RaceExportDTO> raceList;
    private List<ResultExportDTO> resultList;
    private List<LapTimeExportDTO> lapTimeList;
}