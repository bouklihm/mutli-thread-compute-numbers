package com.marou.computenumbers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.marou.computenumbers.exception.ExpiredTimestampException;
import com.marou.computenumbers.model.ConcurrentStatistic;
import com.marou.computenumbers.model.InputCollection;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ComputeNumbersApplication.class)
@AutoConfigureMockMvc
class ComputeNumberServiceTest {

  @InjectMocks
  private ComputeNumberService service;

  @Test
  public void shouldThrowExpiredTimestampExceptionForTimestampsOlderThan60Secs() {

    InputCollection input = InputCollection.builder()
        .timestamp(Instant.now().minus(61, ChronoUnit.SECONDS))
        .xValue(0.0442672968)
        .yValue(1282509067)
        .build();

    assertThrows(ExpiredTimestampException.class, () -> service.compute(input));

  }

  @Test
  public void shouldComputeNumberCorrectly() {

    InputCollection input = InputCollection.builder()
        .timestamp(Instant.now())
        .xValue(0.0442672968)
        .yValue(1282509067)
        .build();

    ConcurrentStatistic expectedStatistic = new ConcurrentStatistic();
    expectedStatistic.plus(0.0442672968, 1282509067);

    ConcurrentStatistic statistic = service.compute(input);

    Assertions.assertEquals(expectedStatistic.getData().getAvgX(), statistic.getData().getAvgX());
    Assertions.assertEquals(expectedStatistic.getData().getAvgY(), statistic.getData().getAvgY());
    Assertions.assertEquals(expectedStatistic.getData().getSumXValue(),
        statistic.getData().getSumXValue());
    Assertions.assertEquals(expectedStatistic.getData().getSumYValue(),
        statistic.getData().getSumYValue());
    Assertions.assertEquals(expectedStatistic.getData().getTotal(), statistic.getData().getTotal());
  }

}
