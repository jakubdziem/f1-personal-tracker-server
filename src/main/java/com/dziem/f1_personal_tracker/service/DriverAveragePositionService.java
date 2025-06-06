package com.dziem.f1_personal_tracker.service;

import com.dziem.f1_personal_tracker.dtos.DriverAveragePositionDTO;

import java.util.List;


public interface DriverAveragePositionService {
    List<DriverAveragePositionDTO> getDrivers();
}
