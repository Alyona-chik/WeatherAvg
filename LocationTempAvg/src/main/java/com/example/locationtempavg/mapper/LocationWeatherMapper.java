package com.example.locationtempavg.mapper;

import com.example.locationtempavg.dto.LocationWeatherDto;
import com.example.locationtempavg.model.LocationWeatherAvg;

public class LocationWeatherMapper {
  static public LocationWeatherDto entityToDto(LocationWeatherAvg locationWeatherAvg) {
    return LocationWeatherDto.builder()
      .location(locationWeatherAvg.getLocation())
      .averageTemp(locationWeatherAvg.getAverageTemp())
      .counterTemp(locationWeatherAvg.getCounterTemp())
      .build();
  }

  static public LocationWeatherAvg dtoToEntity(LocationWeatherDto locationWeatherAvg) {
    return LocationWeatherAvg.builder()
      .location(locationWeatherAvg.getLocation())
      .averageTemp(locationWeatherAvg.getAverageTemp())
      .counterTemp(locationWeatherAvg.getCounterTemp())
      .build();
  }
}
