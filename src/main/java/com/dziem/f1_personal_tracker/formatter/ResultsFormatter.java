package com.dziem.f1_personal_tracker.formatter;

import com.dziem.f1_personal_tracker.model.Result;
import com.dziem.f1_personal_tracker.repository.DriverRepository;
import com.dziem.f1_personal_tracker.repository.RaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
//resultId,raceId,driverId,constructorId,number,grid,position,positionText,positionOrder,points,laps,time,milliseconds,fastestLap,rank,fastestLapTime,fastestLapSpeed,statusId
//1,18,1,1,22,1,1,"1",1,10,58,"1:34:50.616",5690616,39,2,"1:27.452","218.300",1
@Component
@AllArgsConstructor
public class ResultsFormatter {
    private final DriverRepository driverRepository;
    private final RaceRepository raceRepository;
    public List<Result> getResults() {
        String csvFile = "src/main/resources/static/results.csv"; // Replace with your actual file path
        String line;
        List<Result> resultList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String headerLine = br.readLine();
            if (headerLine == null) return resultList;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].replaceAll("^\"|\"$", "");
                }
                if(fields[12].equals("\\N") || fields[6].equals("\\N")) {
                    continue;
                }
                long millis = Long.parseLong(fields[12]);


                LocalTime time = Instant.ofEpochMilli(millis)
                        .atZone(ZoneId.of("Europe/London"))
                        .toLocalTime();
                resultList.add(Result.builder()
                        .position(Integer.parseInt(fields[6]))
                        .raceTime(time)
                        .race(raceRepository.findById(Integer.parseInt(fields[1])).orElse(null))
                        .driver(driverRepository.findById(Integer.parseInt(fields[2])).orElse(null))
                        .build());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
