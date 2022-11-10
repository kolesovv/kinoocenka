package com.kinoocenka.ratings_data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RatingsDataApplication {

  public static void main(String[] args) {

    SpringApplication.run(RatingsDataApplication.class, args);
  }
}
