package com.kinoocenka.ratings_data.controller;

import com.kinoocenka.ratings_data.model.Rating;
import com.kinoocenka.ratings_data.model.UserRating;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rating")
public class RatingController {

  @GetMapping("/{movieId}")
  public Rating getRating(@PathVariable("movieId") Long movieId) {

    return Rating.builder()
        .movieId(movieId)
        .review("Good serial!")
        .assessment(10)
        .build();
  }

  @GetMapping("/user/{userId}")
  public UserRating getUserRatings(@PathVariable("userId") Long userId) {

    return UserRating.builder()
        .userId(userId)
        .ratings(List.of(
            Rating.builder()
                .movieId(1L)
                .review("Good!")
                .assessment(8)
                .build(),
            Rating.builder()
                .movieId(2L)
                .review("Best ever!")
                .assessment(10)
                .build()
        ))
        .build();
  }
}
