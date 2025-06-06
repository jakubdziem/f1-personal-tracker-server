package com.dziem.f1_personal_tracker.formatter;

import com.dziem.f1_personal_tracker.model.Race;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Component
public class RaceFormatter {
    //raceId,year,round,circuitId,name,date,time,url,fp1_date,fp1_time,fp2_date,fp2_time,fp3_date,fp3_time,quali_date,quali_time,sprint_date,sprint_time
    //1,2009,1,1,"Australian Grand Prix","2009-03-29","06:00:00","http://en.wikipedia.org/wiki/2009_Australian_Grand_Prix",\N,\N,\N,\N,\N,\N,\N,\N,\N,\N
    public List<Race> getRaces() {
        String csvFile = "src/main/resources/static/races.csv"; // Replace with your actual file path
        String line;
        List<Race> raceList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String headerLine = br.readLine();
            if (headerLine == null) return raceList;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].replaceAll("^\"|\"$", "");
                }
                raceList.add(Race.builder()
                        .year(Year.of(Integer.parseInt(fields[1])))
                        .name(fields[4])
                        .build());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return raceList;
    }
}
