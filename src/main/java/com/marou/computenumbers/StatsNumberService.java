package com.marou.computenumbers;

import com.marou.computenumbers.event.RawDataEventPublisher;
import com.marou.computenumbers.model.DataStatistic;
import com.marou.computenumbers.model.InputCollection;
import com.marou.computenumbers.model.OutputStats;
import com.marou.computenumbers.model.RawData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsNumberService {

  @Autowired
  private RawDataEventPublisher rawDataEventPublisher;

  public void publishNumberCollectionEvent(RawData rawData) {
    InputCollection inputCollection = rawData.toEntity(rawData);

    rawDataEventPublisher.publishInputCollectionEvent(inputCollection);
  }

  public OutputStats getStatistics() {
    //TODO consume compute
    DataStatistic data = new DataStatistic();
    return data.fromEntity(data);
  }

}
