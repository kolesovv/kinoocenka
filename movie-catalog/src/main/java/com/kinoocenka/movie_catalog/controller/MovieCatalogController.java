package com.kinoocenka.movie_catalog.controller;

import com.kinoocenka.movie_catalog.model.CatalogItem;
import com.kinoocenka.movie_catalog.model.Movie;
import com.kinoocenka.movie_catalog.model.UserRating;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/movie-catalog")
public class MovieCatalogController {

  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/{userId}")
  public List<CatalogItem> getCatalog(@PathVariable("userId") Long userId) {

    UserRating userRating = restTemplate.getForObject("http://ratings-data/api/v1/rating/user/" + userId,
        UserRating.class);

    return userRating.getRatings()
        .stream()
        .map(rating -> {
          Movie movie = restTemplate.getForObject("http://movie-info/api/v1/movie/" + rating.getMovieId(),
              Movie.class);
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
