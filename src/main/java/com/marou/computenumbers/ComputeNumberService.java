package com.marou.computenumbers;

import static java.time.OffsetDateTime.now;

import com.marou.computenumbers.exception.ExpiredTimestampException;
import com.marou.computenumbers.model.ConcurrentStatistic;
import com.marou.computenumbers.model.InputCollection;
import com.marou.computenumbers.model.OutputStats;
import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
public class ComputeNumberService {

  private final ConcurrentStatistic stats = new ConcurrentStatistic();


  public ConcurrentStatistic compute(InputCollection input) {

    final double xValue = input.getXValue();
    final int yValue = input.getYValue();

    val millisToSixtySeconds = Duration
        .between(
            now().toInstant(),
            input.getTimestamp())
        .abs()
        .toMillis();

    validateTimestamp(millisToSixtySeconds);

    computeStats(xValue, yValue, millisToSixtySeconds);

    return stats;
  }

  private void computeStats(double xValue, int yValue, long millisToSixtySeconds) {
    stats.plus(xValue, yValue);

    new Timer().schedule(
        new TimerTask() {
          @Override
          public void run() {
            stats.minus(xValue, yValue);
          }
        },
        60000 - millisToSixtySeconds);
  }

  private void validateTimestamp(long millisToSixtySeconds) {
    if (millisToSixtySeconds >= 60_000) {
      throw new ExpiredTimestampException();
    }
  }

  public OutputStats getStatistics() {
    return stats.getData().fromEntity();
  }

}
