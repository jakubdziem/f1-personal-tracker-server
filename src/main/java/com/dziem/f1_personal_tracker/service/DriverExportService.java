package com.dziem.f1_personal_tracker.service;

import com.dziem.f1_personal_tracker.dtos.DriverExportDTO;

import java.util.List;

public interface DriverExportService {
    List<DriverExportDTO> exportAllDrivers();
}
