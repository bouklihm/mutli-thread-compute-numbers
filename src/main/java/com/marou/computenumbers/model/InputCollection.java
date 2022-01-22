package com.marou.computenumbers.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputCollection {

  private Instant timestamp;

  private double xValue;

  private int yValue;

}
