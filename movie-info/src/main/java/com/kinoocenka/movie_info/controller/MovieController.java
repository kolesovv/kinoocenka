package com.kinoocenka.movie_info.controller;

import com.kinoocenka.movie_info.model.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

  @GetMapping("/{movieId}")
  public Movie getMovieInfo(@PathVariable("movieId") Long movieId) {

    return Movie.builder()
        .id(movieId)
        .title("Very strange things")
        .description("Mystical story")
        .year(2016)
        .build();
  }
}
