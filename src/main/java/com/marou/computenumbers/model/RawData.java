package com.marou.computenumbers.model;

import com.fasterxml.jackson.annotation.JsonValue;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawData {

  @JsonValue
  @NonNull
  private String payload;

  public InputCollection toEntity() {
    String[] splitData = payload.split(",");

    Instant timeStamp = Instant.ofEpochMilli(Long.parseLong(splitData[0]));
    double xValue = Double.parseDouble(splitData[1]);
    int yValue = Integer.parseInt(splitData[2]);

    return InputCollection.builder()
        .timestamp(timeStamp)
        .xValue(xValue)
        .yValue(yValue)
        .build();
  }

}
