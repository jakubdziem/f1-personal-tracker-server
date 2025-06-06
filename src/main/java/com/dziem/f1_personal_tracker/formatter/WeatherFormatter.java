package com.dziem.f1_personal_tracker.formatter;

import com.dziem.f1_personal_tracker.dtos.MeetingApiDTO;
import com.dziem.f1_personal_tracker.dtos.MeetingWithWeather;
import com.dziem.f1_personal_tracker.dtos.WeatherApiDTO;
import com.dziem.f1_personal_tracker.dtos.WeatherDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Component
public class WeatherFormatter {
    private final RestTemplate restTemplate = new RestTemplate();

    public List<MeetingWithWeather> getWeather() {

        String baseMeetingUrl = "https://api.openf1.org/v1/meetings?year=";
        String meetingUrl2023 = baseMeetingUrl + "2023";
        String meetingUrl2024 = baseMeetingUrl + "2024";
        String weatherURL = "https://api.openf1.org/v1/weather";

        WeatherApiDTO[] weatherData = restTemplate.getForObject(weatherURL, WeatherApiDTO[].class);
        System.out.println("weatherData");

        MeetingApiDTO[] meetingData2023 = restTemplate.getForObject(meetingUrl2023, MeetingApiDTO[].class);
        System.out.println("meetingData2023");

        MeetingApiDTO[] meetingData2024 = restTemplate.getForObject(meetingUrl2024, MeetingApiDTO[].class);
        System.out.println("meetingData2024");


        List<WeatherApiDTO> weatherApiDTOList = new ArrayList<>(List.of(weatherData));
        Map<Integer, List<WeatherApiDTO>> weatherApiDTOMap = weatherApiDTOList.stream().collect(groupingBy(WeatherApiDTO::getMeetingKey));
        List<MeetingApiDTO> meetingApiList = new ArrayList<>(List.of(meetingData2023));
        meetingApiList.addAll(List.of(meetingData2024));
        List<MeetingWithWeather> meetingWithWeathers = new ArrayList<>();

        for (Map.Entry<Integer, List<WeatherApiDTO>> weatherApiDTOSFromMeeting : weatherApiDTOMap.entrySet()) {
            List<WeatherApiDTO> weatherApiDTOS = weatherApiDTOSFromMeeting.getValue();
            List<WeatherDTO> weatherDTOList = new ArrayList<>();
            for(int i = 0; i < weatherApiDTOS.size(); i++) {
                WeatherApiDTO weatherApiDTO = weatherApiDTOS.get(i);
                LocalDate time = OffsetDateTime.parse(weatherApiDTO.getDate()).toLocalDate();
                weatherDTOList.add(WeatherDTO.builder()
                        .time(time)
                        .raceDTO(null)
                        .rainfall(weatherApiDTO.getRainfall())
                        .trackTemperature(weatherApiDTO.getTrackTemperature())
                        .airTemperature(weatherApiDTO.getAirTemperature())
                        .windSpeed(weatherApiDTO.getWindSpeed())
                        .lapCount(i+1)
                        .humidity(weatherApiDTO.getHumidity())
                        .build());
            }
            List<MeetingApiDTO> list = meetingApiList.stream()
                    .filter(m -> m.getMeetingKey().equals(weatherApiDTOSFromMeeting.getKey()))
                    .toList();
            if(!list.isEmpty()) {
                meetingWithWeathers.add(MeetingWithWeather.builder()
                                .meetingName(list.getFirst().getMeetingName())
                        .weatherDTOList(weatherDTOList)
                        .build());
            }
        }
        return meetingWithWeathers;
    }
}
