package com.dziem.f1_personal_tracker.controller;

import com.dziem.f1_personal_tracker.dtos.DriverExportDTO;
import com.dziem.f1_personal_tracker.service.DriverExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/export")
@RequiredArgsConstructor
public class DriverExportController {


    private final DriverExportService driverExportService;

    @GetMapping("/drivers")
    public List<DriverExportDTO> exportAllDrivers() {
        return driverExportService.exportAllDrivers();
    }
}