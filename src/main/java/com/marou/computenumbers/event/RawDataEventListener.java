package com.marou.computenumbers.event;

import com.marou.computenumbers.ComputeNumberService;
import com.marou.computenumbers.model.InputCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RawDataEventListener implements ApplicationListener<InputCollectionEvent> {

  @Autowired
  private ComputeNumberService computeNumberService;

  private static final Logger logger = LoggerFactory.getLogger(RawDataEventListener.class);

  @Override
  public void onApplicationEvent(InputCollectionEvent event) {
    logger.info(String.format("RawDataSpringEventListener consumed message: %s", event.getTimeStamp()));
    computeNumberService.compute(
        InputCollection.builder()
            .xValue(event.getXValue())
            .yValue(event.getYValue())
            .timestamp(event.getTimeStamp())
            .build()
    );
  }

}
