package com.dziem.f1_personal_tracker.controller;

import com.dziem.f1_personal_tracker.dtos.*;
import com.dziem.f1_personal_tracker.service.DriverImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/import")
@RequiredArgsConstructor
public class DriverImportController {
    private final DriverImportService driverImportService;

    @PostMapping("/drivers")
    public ResponseEntity<String> importDrivers(@RequestBody List<DriverImportDTO> dtosList) {
        return driverImportService.importDrivers(dtosList);
    }

}