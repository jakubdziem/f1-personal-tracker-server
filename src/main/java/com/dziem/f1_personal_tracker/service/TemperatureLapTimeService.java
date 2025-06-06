package com.dziem.f1_personal_tracker.service;

import com.dziem.f1_personal_tracker.dtos.TemperatureLapTimeDTO;

import java.util.List;

public interface TemperatureLapTimeService {
    public List<TemperatureLapTimeDTO> getTemperatureLapTimeDTOs();
}
