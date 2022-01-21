package com.marou.computenumbers.event;

import com.marou.computenumbers.model.InputCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class RawDataEventPublisher {

  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  public void publishInputCollectionEvent(InputCollection input) {

    InputCollectionEvent event = new InputCollectionEvent(
        this,
        input.getTimestamp(),
        input.getXValue(),
        input.getYValue()
    );

    applicationEventPublisher.publishEvent(event);
  }

}
