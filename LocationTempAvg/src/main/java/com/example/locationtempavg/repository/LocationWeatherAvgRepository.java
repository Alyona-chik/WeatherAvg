package com.example.locationtempavg.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import com.example.locationtempavg.model.LocationWeatherAvg;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

public interface LocationWeatherAvgRepository extends CrudRepository<LocationWeatherAvg, Long> {
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<LocationWeatherAvg> findByLocation(String location);
}
