package com.dziem.f1_personal_tracker.service;

import com.dziem.f1_personal_tracker.dtos.DriverAveragePositionDTO;
import com.dziem.f1_personal_tracker.model.Driver;
import com.dziem.f1_personal_tracker.model.Race;
import com.dziem.f1_personal_tracker.model.Result;
import com.dziem.f1_personal_tracker.repository.DriverRepository;
import com.dziem.f1_personal_tracker.repository.RaceRepository;
import com.dziem.f1_personal_tracker.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverAveragePositionServiceImpl implements DriverAveragePositionService{
    private final DriverRepository driverRepository;
    private final ResultRepository resultRepository;
    private final RaceRepository raceRepository;

    public List<DriverAveragePositionDTO> getDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        List<Result> results = resultRepository.findAll();

        List<Race> racesWithWeather = raceRepository.findAllWithWeather();

        Map<Integer, Race> raceMap = racesWithWeather.stream()
                .collect(Collectors.toMap(Race::getId, r -> r));

        results.forEach(result -> {
            Race race = result.getRace();
            if (race != null) {
                Race loadedRace = raceMap.get(race.getId());
                result.setRace(loadedRace);
            }
        });

        Map<Driver, List<Result>> resultsByDriver = results.stream()
                .filter(r -> r.getPosition() != null)
                .collect(Collectors.groupingBy(Result::getDriver));

        return drivers.stream()
                .map(driver -> {
                    List<Result> driverResults = resultsByDriver.getOrDefault(driver, List.of());

                    float average = (float) driverResults.stream()
                            .mapToInt(Result::getPosition)
                            .average()
                            .orElse(0.0);

                    float rainyAverage = (float) driverResults.stream()
                            .filter(r -> {
                                Race race = r.getRace();
                                return race != null && race.getWeatherList() != null &&
                                        race.getWeatherList().stream()
                                                .anyMatch(w -> w.getRainfall() > 0);
                            })
                            .mapToInt(Result::getPosition)
                            .average()
                            .orElse(0.0);

                    DriverAveragePositionDTO dto = new DriverAveragePositionDTO();
                    dto.setDriverName(driver.getName());
                    dto.setDrvAvgPosition(average);
                    dto.setRainyAvgPosition(rainyAverage);
                    return dto;
                })
                .toList();
    }
}

