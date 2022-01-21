package com.marou.computenumbers.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class InputCollectionEvent extends ApplicationEvent {

  private int timeStamp;

  private double xValue;

  private int yValue;

  public InputCollectionEvent(Object source, int timeStamp, double xValue, int yValue) {
    super(source);
    this.timeStamp = timeStamp;
    this.xValue = xValue;
    this.yValue = yValue;
  }

}
