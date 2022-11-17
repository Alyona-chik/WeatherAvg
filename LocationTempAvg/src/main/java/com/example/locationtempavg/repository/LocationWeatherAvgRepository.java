package com.example.locationtempavg.repository;

import com.example.locationtempavg.model.LocationWeatherAvg;

import org.springframework.data.repository.CrudRepository;

public interface LocationWeatherAvgRepository extends CrudRepository<LocationWeatherAvg, Long> {
  LocationWeatherAvg findByLocation(String location);
}
