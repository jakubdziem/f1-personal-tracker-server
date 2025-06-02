package com.dziem.f1_personal_tracker.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class MeetingApiDTO {
    @JsonProperty("meeting_key")
    Integer meetingKey;
    @JsonProperty("meeting_name")
    String meetingName;
    @JsonProperty("date_start")
    String dateStart;
}
