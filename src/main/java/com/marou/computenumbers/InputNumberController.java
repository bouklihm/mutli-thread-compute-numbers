package com.marou.computenumbers;

import com.marou.computenumbers.model.OutputStats;
import com.marou.computenumbers.model.RawData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/compute-numbers")
public class InputNumberController {

  @Autowired
  private StatsNumberService service;

  @PostMapping(value = "/event")
  public ResponseEntity<?> publishNumberCollection(
      @RequestBody RawData rawData) {
    service.publishNumberCollectionEvent(rawData);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @GetMapping(value = "/stats")
  public ResponseEntity<OutputStats> getStatistics() {
    OutputStats data = service.getStatistics();
    return ResponseEntity.ok().body(data);
  }

}
