package com.example.locationtempavg.service.impl;

import javax.transaction.Transactional;

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
  @Transactional
  public LocationWeatherDto changeAvgTemp(String location, double temp) {
    LocationWeatherAvg locationWeather = null;
    while (locationWeather == null) {
      locationWeather = repository.findByLocation(location).orElseGet(
        () -> LocationWeatherAvg.builder()
          .location(location)
          .averageTemp(temp)
          .counterTemp(1)
          .build());
    }

    if (locationWeather.getId() != null) {
      locationWeather = updateLocationWeather(location, temp, locationWeather);
    }

    return LocationWeatherMapper.entityToDto(repository.save(locationWeather));
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

  private double calculateNewAvgTemp(double newTemp, int counterTemp, double avgTemp) {
    return (avgTemp * counterTemp + newTemp) / (counterTemp + 1);
  }
}
