package com.example.locationtempavg.controller;

import com.example.locationtempavg.dto.LocationWeatherDto;
import com.example.locationtempavg.service.LocationWeatherAvgService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class LocationWeatherController {
  private final LocationWeatherAvgService service;

  @PostMapping("/{location}/{temp}")
  public ResponseEntity<LocationWeatherDto> updateWeather(@PathVariable String location,
    @PathVariable double temp) {
    LocationWeatherDto locationWeatherDto = null;
    try {
      locationWeatherDto = service.changeAvgTemp(location, temp);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return new ResponseEntity<>(locationWeatherDto, HttpStatus.OK);
  }
}
