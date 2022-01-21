package com.marou.computenumbers.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawData {

  @JsonProperty
  @NonNull
  private String payload;

  public InputCollection toEntity(RawData data){
    String[] splitData = data.payload.split(",");
    int timeStamp = Integer.parseInt(splitData[0]);
    double xValue = Double.parseDouble(splitData[1]);
    int yValue = Integer.parseInt(splitData[2]);

    return InputCollection.builder()
        .timestamp(timeStamp)
        .xValue(xValue)
        .yValue(yValue)
        .build();
  }

}
