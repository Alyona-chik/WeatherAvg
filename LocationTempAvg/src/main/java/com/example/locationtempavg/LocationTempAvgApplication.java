package com.example.locationtempavg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication

public class LocationTempAvgApplication {

  public static void main(String[] args) {
    SpringApplication.run(LocationTempAvgApplication.class, args);
  }

}
