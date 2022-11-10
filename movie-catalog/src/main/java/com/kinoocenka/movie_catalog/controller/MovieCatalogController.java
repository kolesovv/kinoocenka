package com.kinoocenka.movie_catalog.controller;

import com.kinoocenka.movie_catalog.model.CatalogItem;
import com.kinoocenka.movie_catalog.model.Movie;
import com.kinoocenka.movie_catalog.model.UserRating;
import com.kinoocenka.movie_catalog.service.MovieInfoService;
import com.kinoocenka.movie_catalog.service.UserRatingService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movie-catalog")
public class MovieCatalogController {

  @Autowired
  MovieInfoService movieInfoService;

  @Autowired
  UserRatingService userRatingService;

  @GetMapping("/{userId}")
  public List<CatalogItem> getCatalog(@PathVariable("userId") Long userId) {

    UserRating userRating = userRatingService.getUserRatingByUserId(userId);

    return userRating.getRatings()
        .stream()
        .map(rating -> {
          Movie movie = movieInfoService.getMovieById(rating.getMovieId());
          return CatalogItem.builder()
              .title(movie.getTitle())
              .description(movie.getDescription())
              .review(rating.getReview())
              .assessment(rating.getAssessment())
              .build();
        })
        .collect(Collectors.toList());
  }
}
