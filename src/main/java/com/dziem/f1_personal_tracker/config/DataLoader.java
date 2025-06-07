package com.dziem.f1_personal_tracker.config;

import com.dziem.f1_personal_tracker.dtos.MeetingWithWeather;
import com.dziem.f1_personal_tracker.formatter.*;
import com.dziem.f1_personal_tracker.model.*;
import com.dziem.f1_personal_tracker.repository.*;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final DriverFormatter driverFormatter;
    private final DriverRepository driverRepository;
    private final WeatherFormatter weatherFormatter;
    private final WeatherRepository weatherRepository;
    private final RaceFormatter raceFormatter;
    private final RaceRepository raceRepository;
    private final ResultsFormatter resultsFormatter;
    private final ResultRepository resultRepository;
    private final LapTimeRepository lapTimeRepository;
    private final LapTimeFormatter lapTimeFormatter;
    private static final Logger logger = LogManager.getLogger(DataLoader.class);

    @Override
    public void run(String... args){
        logger.info("DataLoader is running...");
        List<Driver> drivers = driverFormatter.getDriversFromCsv();
        driverRepository.saveAll(drivers);
        logger.info("Finished driver saving");

        List<Race> races = raceFormatter.getRaces();
        raceRepository.saveAll(races);
        logger.info("Finished race saving");


        List<Result> results = resultsFormatter.getResults();
        resultRepository.saveAll(results);
        logger.info("Finished result saving");


        List<MeetingWithWeather> meetingWithWeathers = weatherFormatter.getWeather();
        List<Race> allRaces = raceRepository.findAll();

        List<Weather> weatherToSave = meetingWithWeathers.stream()
                .flatMap(meeting -> meeting.getWeatherDTOList().stream()
                        .map(dto -> Weather.builder()
                                .time(dto.getTime())
                                .race(findRaceForMeeting(meeting.getMeetingName(), allRaces))
                                .rainfall(dto.getRainfall())
                                .trackTemperature(dto.getTrackTemperature())
                                .airTemperature(dto.getAirTemperature())
                                .windSpeed(dto.getWindSpeed())
                                .lapCount(dto.getLapCount())
                                .humidity(dto.getHumidity())
                                .build()))
                .toList();
        logger.info("Finished weather fetching");


        weatherRepository.saveAll(weatherToSave);

        Map<Integer, Race> raceMap = raceRepository.findAll().stream()
                .collect(Collectors.toMap(Race::getId, r -> r));

        List<LapTime> lapTimes = lapTimeFormatter.getLapTimes(raceMap);
        lapTimeRepository.saveAll(lapTimes);
        logger.info("Finished");


    }
    private Race findRaceForMeeting(String meetingName, List<Race> allRaces) {
        String normalizedName = meetingName.trim().toLowerCase();

        return allRaces.stream()
                .filter(race -> race.getName() != null && race.getName().trim().toLowerCase().equals(normalizedName))
                .findFirst()
                .orElse(null);
    }
//    SELECT *
//	FROM driver as d inner join result as r on d.id = r.driver_id
//	inner join race as p on p.id = r.race_id where p.year > 2022 order by p.year, p.name,r.position
}
