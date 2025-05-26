package com.dziem.f1_personal_tracker.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DriverDTO {
    Integer id;
    String name;
    List<RaceDTO> raceList;
}
