package com.dziem.f1_personal_tracker.controller;

import com.dziem.f1_personal_tracker.dtos.DriverExportDTO;
import com.dziem.f1_personal_tracker.dtos.LapTimeExportDTO;
import com.dziem.f1_personal_tracker.dtos.RaceExportDTO;
import com.dziem.f1_personal_tracker.dtos.ResultExportDTO;
import com.dziem.f1_personal_tracker.repository.DriverRepository;
import com.dziem.f1_personal_tracker.repository.LapTimeRepository;
import com.dziem.f1_personal_tracker.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/export")
@RequiredArgsConstructor
public class DriverExportController {

    private final DriverRepository driverRepository;
    private final ResultRepository resultRepository;
    private final LapTimeRepository lapTimeRepository;

    @GetMapping("/drivers")
    public List<DriverExportDTO> exportAllDrivers() {
        return driverRepository.findAll().stream().map(driver -> {
            DriverExportDTO dto = new DriverExportDTO();
            dto.setName(driver.getName());

            dto.setRaceList(driver.getRaceList().stream().map(race -> {
                RaceExportDTO r = new RaceExportDTO();
                r.setName(race.getName());
                r.setYear(race.getYear());
                r.setLapsQuantity(race.getLapsQuantity());
                return r;
            }).toList());

            dto.setResultList(resultRepository.findAllByDriver(driver).stream().map(result -> {
                ResultExportDTO res = new ResultExportDTO();
                res.setPosition(result.getPosition());
                res.setRaceTime(result.getRaceTime());
                res.setRaceName(result.getRace().getName());
                return res;
            }).toList());

            // LapTimes
            dto.setLapTimeList(lapTimeRepository.findAllByDriver(driver).stream().map(lap -> {
                LapTimeExportDTO lapDto = new LapTimeExportDTO();
                lapDto.setLapNumber(lap.getLap());
                lapDto.setMilliseconds(lap.getMilliseconds());
                lapDto.setRaceName(lap.getRace().getName());
                return lapDto;
            }).toList());

            return dto;
        }).toList();
    }
}