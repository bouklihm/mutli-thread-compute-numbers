package com.marou.computenumbers;

import com.marou.computenumbers.model.DataStatistic;
import com.marou.computenumbers.model.InputCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
public class ComputeNumberService {


  public void compute(InputCollection input) {
    List<InputCollection> inputList = Collections.synchronizedList(new ArrayList<>());
    List<DataStatistic> computeList = Collections.synchronizedList(new ArrayList<>());
    addInput(inputList, input);
    sumInput(computeList, input);
  }

  private synchronized void addInput(List<InputCollection> list, InputCollection input) {
    list.add(input);
  }

  private synchronized void sumInput(List<DataStatistic> list, InputCollection input) {
    DataStatistic data = DataStatistic.builder()
        .total(1)
        .sumXValue(input.getXValue())
        .sumYValue(input.getYValue())
        .build();

    if (list.isEmpty()) {
      list.add(data);
      return;
    }

    int newTotal = list.get(0).getTotal() + data.getTotal();
    double newXValue = list.get(0).getSumXValue() + data.getSumXValue();
    long newYValue = list.get(0).getSumYValue() + data.getSumYValue();

    list.get(0).setTotal(newTotal);
    list.get(0).setSumXValue(newXValue);
    list.get(0).setSumYValue(newYValue);
  }

}
