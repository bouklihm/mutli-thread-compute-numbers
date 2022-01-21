package com.marou.computenumbers.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataStatistic {

  private int total;

  private double sumXValue;

  private long sumYValue;

  public OutputStats fromEntity(DataStatistic data) {
    int total = data.getTotal();
    double sumXValue = data.getSumXValue();
    double avgXvalue = sumXValue / total;
    long sumYvalue = data.getSumYValue();
    double avgYvalue = sumYvalue / (long) total;

    String output = String.join(",", List.of(
        ((Integer) total).toString(),
        ((Double) sumXValue).toString(),
        ((Double) avgXvalue).toString(),
        ((Long) sumYvalue).toString(),
        ((Double) avgYvalue).toString()));

    return new OutputStats(output);
  }

}
