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

  private ConcurrentStatistic stats = new ConcurrentStatistic();


  public void compute(InputCollection input) {

    val millisTosixtySeconds = Duration
        .between(
            now().toInstant(),
            input.getTimestamp())
        .toMillis();

//    if (millisTosixtySeconds >= 60_000) {
//      throw new ExpiredTimestampException();
//    }

    final double xValue = input.getXValue();
    final int yValue = input.getYValue();

    stats.plus(xValue, yValue);

    new Timer().schedule(
        new TimerTask() {
          @Override
          public void run() {
            stats.minus(xValue, yValue);
          }
        },
        10_000);
//        60_000 - millisTosixtySeconds);
  }

  public OutputStats getStatistics() {
    return stats.getData().fromEntity();
  }

}
