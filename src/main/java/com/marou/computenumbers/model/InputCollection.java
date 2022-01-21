package com.marou.computenumbers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputCollection {

  private int timestamp;

  private double xValue;

  private int yValue;

}
