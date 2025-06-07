package com.dziem.f1_personal_tracker.controller;

import com.dziem.f1_personal_tracker.dtos.DriverAveragePositionDTO;
import com.dziem.f1_personal_tracker.dtos.TemperatureLapTimeDTO;
import com.dziem.f1_personal_tracker.service.DriverAveragePositionService;
import com.dziem.f1_personal_tracker.service.TemperatureLapTimeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DriverController {
    private final DriverAveragePositionService driverAveragePositionService;
    private final TemperatureLapTimeService temperatureLapTimeService;

    @GetMapping("/drivers")
    public List<DriverAveragePositionDTO> getDrivers() {
        return driverAveragePositionService.getDrivers();
    }
    @GetMapping("/weather")
    public List<TemperatureLapTimeDTO> getTemperatureLapTimeDTOS() {
        return temperatureLapTimeService.getTemperatureLapTimeDTOs();
    }
}
