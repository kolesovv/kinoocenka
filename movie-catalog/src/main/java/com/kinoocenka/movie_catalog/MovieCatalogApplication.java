package com.kinoocenka.movie_catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MovieCatalogApplication {

  public static void main(String[] args) {

    SpringApplication.run(MovieCatalogApplication.class, args);
  }
}
