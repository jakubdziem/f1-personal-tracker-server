package com.dziem.f1_personal_tracker.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class WeatherApiDTO {
    @JsonProperty("meeting_key")
    Integer meetingKey;
    String date;
    Integer rainfall;
    @JsonProperty("track_temperature")
    Float trackTemperature;
    @JsonProperty("air_temperature")
    Float airTemperature;
    @JsonProperty("wind_speed")
    Float windSpeed;
    Integer humidity;
}
