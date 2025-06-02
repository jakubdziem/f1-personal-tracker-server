package com.dziem.f1_personal_tracker.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MeetingWithWeather {
    String meetingName;
    List<WeatherDTO> weatherDTOList;
}
