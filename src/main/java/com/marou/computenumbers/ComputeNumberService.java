package com.marou.computenumbers;

import static java.lang.Math.abs;
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
    val millisTosixtySeconds = abs(Duration
        .between(
            now().toInstant(),
            input.getTimestamp())
        .toMillis());

    validateTimestamp(millisTosixtySeconds);

    computeStats(xValue, yValue, millisTosixtySeconds);

    return stats;
  }

  private void computeStats(double xValue, int yValue, long millisTosixtySeconds) {
    stats.plus(xValue, yValue);

    new Timer().schedule(
        new TimerTask() {
          @Override
          public void run() {
            stats.minus(xValue, yValue);
          }
        },
        60_000 - millisTosixtySeconds);
  }

  private void validateTimestamp(long millisTosixtySeconds) {
    if (millisTosixtySeconds >= 60_000) {
      throw new ExpiredTimestampException();
    }
  }

  public OutputStats getStatistics() {
    return stats.getData().fromEntity();
  }

}
