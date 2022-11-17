package com.example.locationtempavg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationWeatherDto {
  private String location;
  private double averageTemp;
  private int counterTemp;
}
