package com.marou.computenumbers.event;

import java.time.Instant;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class InputCollectionEvent extends ApplicationEvent {

  private Instant timeStamp;

  private double xValue;

  private int yValue;

  public InputCollectionEvent(Object source, Instant timeStamp, double xValue, int yValue) {
    super(source);
    this.timeStamp = timeStamp;
    this.xValue = xValue;
    this.yValue = yValue;
  }

}
