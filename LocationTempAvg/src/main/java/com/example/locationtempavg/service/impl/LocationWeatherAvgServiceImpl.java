package com.example.locationtempavg.service.impl;

import java.util.Optional;

import com.example.locationtempavg.dto.LocationWeatherDto;
import com.example.locationtempavg.mapper.LocationWeatherMapper;
import com.example.locationtempavg.model.LocationWeatherAvg;
import com.example.locationtempavg.repository.LocationWeatherAvgRepository;
import com.example.locationtempavg.service.LocationWeatherAvgService;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocationWeatherAvgServiceImpl implements LocationWeatherAvgService {

  private final LocationWeatherAvgRepository repository;

  @Override
  public LocationWeatherDto update(String location, double temp) {
    LocationWeatherAvg oldLocationWeather = repository.findByLocation(location);
    if(oldLocationWeather == null) {
     throw new RuntimeException(String.format("Location %s is not found", location));
    }

    double newAvgTemp = calculateNewAvgTemp(temp, oldLocationWeather.getCounterTemp(), oldLocationWeather.getAverageTemp());

    LocationWeatherAvg newWeatherAvg = LocationWeatherAvg.builder()
      .id(oldLocationWeather.getId())
      .location(location)
      .averageTemp(newAvgTemp)
      .averageTemp(oldLocationWeather.getAverageTemp() + 1)
      .build();

    return LocationWeatherMapper.entityToDto(repository.save(newWeatherAvg));
  }

  private double calculateNewAvgTemp(double newTemp, int counterTemp, double avgTemp) {
    return (avgTemp * counterTemp + newTemp) / (counterTemp + 1);
  }
}
