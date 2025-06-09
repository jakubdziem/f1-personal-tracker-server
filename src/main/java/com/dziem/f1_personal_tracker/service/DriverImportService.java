package com.dziem.f1_personal_tracker.service;

import com.dziem.f1_personal_tracker.dtos.DriverImportDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DriverImportService {
    ResponseEntity<String> importDrivers(List<DriverImportDTO> dtosList);
}
