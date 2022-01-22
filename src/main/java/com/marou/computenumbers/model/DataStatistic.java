package com.marou.computenumbers.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DataStatistic {

  private final int total;

  private final double sumXValue;

  private final long sumYValue;

  public double getAvgX() {
    if (total == 0) {
      return 0;
    }
    return sumXValue / total;
  }

  public double getAvgY() {
    if (total == 0) {
      return 0;
    }
    return (sumYValue * 1.0) / total;
  }

  public DataStatistic plus(double x, int y) {
    return new DataStatistic(total + 1, sumXValue + x, sumYValue + y);
  }

  public DataStatistic minus(double x, int y) {
    return new DataStatistic(total - 1, Math.max(0, sumXValue - x), sumYValue - y);
  }

  public OutputStats fromEntity() {

    String output = String.join(",", List.of(
        ((Integer) this.total).toString(),
        ((Double) this.sumXValue).toString(),
        ((Double) getAvgX()).toString(),
        ((Long) this.sumYValue).toString(),
        ((Double) getAvgY()).toString()));

    return new OutputStats(output);
  }

}
