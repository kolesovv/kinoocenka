package com.kinoocenka.movie_catalog.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRating {

  private Long userId;
  private List<Rating> ratings;
}
