package com.dziem.f1_personal_tracker.service;

import com.dziem.f1_personal_tracker.dtos.TemperatureLapTimeDTO;
import com.dziem.f1_personal_tracker.model.LapTime;
import com.dziem.f1_personal_tracker.model.Race;
import com.dziem.f1_personal_tracker.model.Weather;
import com.dziem.f1_personal_tracker.repository.LapTimeRepository;
import com.dziem.f1_personal_tracker.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TemperatureLapTimeServiceImpl implements TemperatureLapTimeService {
    private final WeatherRepository weatherRepository;
    private final LapTimeRepository lapTimeRepository;

    public List<TemperatureLapTimeDTO> getTemperatureLapTimeDTOs() {
        List<LapTime> lapTimes = lapTimeRepository.findAll().stream()
                .filter(lapTime -> {
                    Race race = lapTime.getRace();
                    return race != null;
                })
                .toList();

        List<Weather> weatherData = weatherRepository.findAll().stream()
                .filter(w -> {
                    Race race = w.getRace();
                    return race != null;
                })
                .toList();

        Map<Float, List<Integer>> tempToLapTimes = new HashMap<>();

        for (Weather w : weatherData) {
            Float temp = w.getTrackTemperature();
            if (temp == null) continue;


            List<LapTime> relevantLapTimes = lapTimes.stream()
                    .filter(l -> l.getRace() != null && w.getRace() != null &&
                            l.getRace().getId().equals(w.getRace().getId()))
                    .toList();


            int avgLapTime = relevantLapTimes.stream()
                    .mapToInt(LapTime::getMilliseconds)
                    .sum() / Math.max(1, relevantLapTimes.size());

            tempToLapTimes.computeIfAbsent(temp, k -> new ArrayList<>()).add(avgLapTime);
        }

        return tempToLapTimes.entrySet().stream()
                .map(entry -> {
                    Float temp = entry.getKey();
                    List<Integer> lapTimesList = entry.getValue();
                    float avg = (float) lapTimesList.stream().mapToInt(Integer::intValue).average().orElse(0);

                    TemperatureLapTimeDTO dto = new TemperatureLapTimeDTO();
                    dto.setTrackTemperature(temp);
                    dto.setAvgLapTimeMs(avg);
                    return dto;
                })
                .toList();
    }
}

