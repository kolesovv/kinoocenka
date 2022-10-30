package com.kinoocenka.movie_catalog.service;

import com.kinoocenka.movie_catalog.model.Movie;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {

  @Autowired
  private RestTemplate restTemplate;

  @HystrixCommand(
      fallbackMethod = "getFallbackMovieById",
      threadPoolKey = "movieInfoPool",
      threadPoolProperties = {
          @HystrixProperty(name = "coreSize", value = "20"),
          @HystrixProperty(name = "maxQueueSize", value = "10")
      }
  )
  public Movie getMovieById(Long movieId) {

    Movie movie = restTemplate.getForObject("http://movie-info/api/v1/movie/" + movieId, Movie.class);
    return movie;
  }

  public Movie getFallbackMovieById(Long movieId) {

    return Movie.builder()
        .id(0L)
        .title("")
        .description("")
        .year(0)
        .build();
  }
}
