package com.dziem.f1_personal_tracker.controller;

import com.dziem.f1_personal_tracker.dtos.*;
import com.dziem.f1_personal_tracker.model.*;
import com.dziem.f1_personal_tracker.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/import")
@RequiredArgsConstructor
public class DriverImportController {

    private final DriverRepository driverRepository;
    private final RaceRepository raceRepository;
    private final ResultRepository resultRepository;
    private final LapTimeRepository lapTimeRepository;

    @PostMapping("/driver")
    public ResponseEntity<String> importDriver(@RequestBody List<DriverImportDTO> dtosList) {
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