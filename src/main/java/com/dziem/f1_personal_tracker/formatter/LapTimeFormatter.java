package com.dziem.f1_personal_tracker.formatter;

import com.dziem.f1_personal_tracker.model.LapTime;
import com.dziem.f1_personal_tracker.model.Race;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class LapTimeFormatter {
    public List<LapTime> getLapTimes(Map<Integer, Race> raceMap) {
        //        raceId,driverId,lap,position,time,milliseconds
        //         841,20,1,1,"1:38.109",98109
        String csvFile = "src/main/resources/static/lap_times.csv";
        String line;
        List<LapTime> lapTimeList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String headerLine = br.readLine();
            if (headerLine == null) return lapTimeList;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].replaceAll("^\"|\"$", "");
                }

                Integer raceId = Integer.valueOf(fields[0]);
                Race race = raceMap.get(raceId);
                if (race == null) continue;

                lapTimeList.add(LapTime.builder()
                        .race(race)
                        .lap(Integer.valueOf(fields[2]))
                        .position(Integer.valueOf(fields[3]))
                        .milliseconds(Integer.valueOf(fields[5]))
                        .build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lapTimeList;

    }
}
