package com.marou.computenumbers;

import com.marou.computenumbers.event.RawDataEventPublisher;
import com.marou.computenumbers.model.InputCollection;
import com.marou.computenumbers.model.RawData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackPressureService {

  @Autowired
  private RawDataEventPublisher rawDataEventPublisher;

  public void publishNumberCollectionEvent(RawData rawData) {
    InputCollection inputCollection = rawData.toEntity();

    rawDataEventPublisher.publishInputCollectionEvent(inputCollection);
  }

}
