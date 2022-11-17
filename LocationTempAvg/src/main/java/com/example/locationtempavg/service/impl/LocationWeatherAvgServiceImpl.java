package com.example.locationtempavg.service.impl;

import com.example.locationtempavg.dto.LocationWeatherDto;
import com.example.locationtempavg.mapper.LocationWeatherMapper;
import com.example.locationtempavg.model.LocationWeatherAvg;
import com.example.locationtempavg.repository.LocationWeatherAvgRepository;
import com.example.locationtempavg.service.LocationWeatherAvgService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocationWeatherAvgServiceImpl implements LocationWeatherAvgService {
  private final LocationWeatherAvgRepository repository;

  @Override
  public LocationWeatherDto changeAvgTemp(String location, double temp) {
    LocationWeatherAvg oldLocationWeather = repository.findByLocation(location);
    LocationWeatherAvg newWeatherAvg = null;
    if(oldLocationWeather == null) {
      newWeatherAvg = createNewLocationWeather(location, temp);
    } else {
      newWeatherAvg = updateLocationWeather(location, temp, oldLocationWeather);
    }
    return LocationWeatherMapper.entityToDto(repository.save(newWeatherAvg));
  }

  private LocationWeatherAvg updateLocationWeather(String location, double temp,
    LocationWeatherAvg oldLocationWeather) {
    double newAvgTemp =
      calculateNewAvgTemp(temp, oldLocationWeather.getCounterTemp(), oldLocationWeather.getAverageTemp());

    LocationWeatherAvg newWeatherAvg = LocationWeatherAvg.builder()
      .id(oldLocationWeather.getId())
      .location(location)
      .averageTemp(newAvgTemp)
      .counterTemp(oldLocationWeather.getCounterTemp() + 1)
      .build();
    return newWeatherAvg;
  }

  private LocationWeatherAvg createNewLocationWeather(String location, double temp) {
    return LocationWeatherAvg.builder()
      .location(location)
      .averageTemp(temp)
      .counterTemp(1)
      .build();
  }

  private double calculateNewAvgTemp(double newTemp, int counterTemp, double avgTemp) {
    return (avgTemp * counterTemp + newTemp) / (counterTemp + 1);
  }
}
