package com.example.locationtempavg.service;

import com.example.locationtempavg.dto.LocationWeatherDto;

public interface LocationWeatherAvgService {
  LocationWeatherDto update(String location, double temp);
}
