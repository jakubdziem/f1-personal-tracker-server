package com.dziem.f1_personal_tracker.service;

import com.dziem.f1_personal_tracker.dtos.DriverExportDTO;
import com.dziem.f1_personal_tracker.dtos.LapTimeExportDTO;
import com.dziem.f1_personal_tracker.dtos.RaceExportDTO;
import com.dziem.f1_personal_tracker.dtos.ResultExportDTO;
import com.dziem.f1_personal_tracker.repository.DriverRepository;
import com.dziem.f1_personal_tracker.repository.LapTimeRepository;
import com.dziem.f1_personal_tracker.repository.ResultRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class DriverExportServiceImpl implements DriverExportService {
    private final DriverRepository driverRepository;
    private final ResultRepository resultRepository;
    private final LapTimeRepository lapTimeRepository;
    @Override
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
                if (result.getRace() != null) {
                    res.setRaceName(result.getRace().getName());
                } else {
                    res.setRaceName("UNKNOWN");
                }
                return res;
            }).toList());

            dto.setLapTimeList(lapTimeRepository.findAllByDriver(driver).stream().map(lap -> {
                LapTimeExportDTO lapDto = new LapTimeExportDTO();
                lapDto.setLapNumber(lap.getLap());
                lapDto.setMilliseconds(lap.getMilliseconds());
                if (lap.getRace() != null) {
                    lapDto.setRaceName(lap.getRace().getName());
                } else {
                    lapDto.setRaceName("UNKNOWN");
                }
                return lapDto;
            }).toList());

            return dto;
        }).toList();
    }
}
