package com.dziem.f1_personal_tracker.service;

import com.dziem.f1_personal_tracker.dtos.DriverImportDTO;
import com.dziem.f1_personal_tracker.model.Driver;
import com.dziem.f1_personal_tracker.model.LapTime;
import com.dziem.f1_personal_tracker.model.Race;
import com.dziem.f1_personal_tracker.model.Result;
import com.dziem.f1_personal_tracker.repository.DriverRepository;
import com.dziem.f1_personal_tracker.repository.LapTimeRepository;
import com.dziem.f1_personal_tracker.repository.RaceRepository;
import com.dziem.f1_personal_tracker.repository.ResultRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class DriverImportServiceImpl implements DriverImportService {
    private final DriverRepository driverRepository;
    private final RaceRepository raceRepository;
    private final ResultRepository resultRepository;
    private final LapTimeRepository lapTimeRepository;
    @Override
    public ResponseEntity<String> importDrivers(List<DriverImportDTO> dtosList) {
        for(DriverImportDTO dto : dtosList) {
            List<Race> races = dto.getRaceList().stream()
                    .map(r -> Race.builder().name(r.getName()).year(Year.of(r.getYear())).lapsQuantity(r.getLapsQuantity()).build())
                    .collect(Collectors.toList());
            raceRepository.saveAll(races);

            Driver driver = Driver.builder().name(dto.getName()).raceList(races).build();
            driverRepository.save(driver);

            List<Result> results = dto.getResultList().stream()
                    .map(res -> Result.builder()
                            .driver(driver)
                            .position(res.getPosition())
                            .raceTime(LocalTime.parse(res.getRaceTime()))
                            .race(raceRepository.findRacesByName(res.getRaceName()).orElse(List.of()).stream().findFirst().orElse(null))
                            .build())
                    .collect(Collectors.toList());
            resultRepository.saveAll(results);

            List<LapTime> lapTimes = dto.getLapTimeList().stream()
                    .map(lt -> LapTime.builder()
                            .driver(driver)
                            .lap(lt.getLapNumber())
                            .milliseconds(lt.getMilliseconds())
                            .race(raceRepository.findRacesByName(lt.getRaceName()).orElse(List.of()).stream().findFirst().orElse(null))
                            .build())
                    .collect(Collectors.toList());
            lapTimeRepository.saveAll(lapTimes);

        }
        return ResponseEntity.ok("Drivers and nested data imported successfully.");
    }
}
