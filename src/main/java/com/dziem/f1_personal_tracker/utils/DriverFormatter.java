package com.dziem.f1_personal_tracker.utils;

import com.dziem.f1_personal_tracker.model.Driver;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
@Component
public class DriverFormatter {
    public List<Driver> getDriversFromCsv() {
        String line;
        List<Driver> driverList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("static/drivers.csv")))) {
            String headerLine = br.readLine();
            if (headerLine == null) return driverList;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].replaceAll("^\"|\"$", "");
                }

                driverList.add(Driver.builder()
                        .name(fields[4] + " " + fields[5])
                        .build());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driverList;
    }
}
