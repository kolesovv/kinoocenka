package com.kinoocenka.ratings_data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

  private Long movieId;
  private String review;
  private Integer assessment;
}
