package com.kinoocenka.movie_catalog.service;

import com.kinoocenka.movie_catalog.model.Rating;
import com.kinoocenka.movie_catalog.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRatingService {

  @Autowired
  private RestTemplate restTemplate;

  @HystrixCommand(
      fallbackMethod = "getFallbackUserRatingByUserId",
      threadPoolKey = "userRatingPool",
      threadPoolProperties = {
          @HystrixProperty(name = "coreSize", value = "20"),
          @HystrixProperty(name = "maxQueueSize", value = "10")
      })
  public UserRating getUserRatingByUserId(Long userId) {

    UserRating userRating =
        restTemplate.getForObject("http://ratings-data/api/v1/rating/user/" + userId, UserRating.class);
    return userRating;
  }

  public UserRating getFallbackUserRatingByUserId(Long userId) {

    return UserRating.builder()
        .userId(userId)
        .ratings(List.of(Rating.builder()
            .movieId(0L)
            .review("")
            .assessment(0)
            .build()))
        .build();
  }
}
