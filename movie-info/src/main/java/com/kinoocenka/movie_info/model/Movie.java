package com.kinoocenka.movie_info.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

  private Long id;
  private String title;
  private String description;
  private Integer year;
}
