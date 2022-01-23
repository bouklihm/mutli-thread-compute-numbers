package com.marou.computenumbers;

import com.marou.computenumbers.model.RawData;
import com.marou.computenumbers.validation.InputConstrain;
import com.marou.computenumbers.validation.InputValidator;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ComputeNumberController {

  private final BackPressureService service;

  private final ComputeNumberService computeNumberService;

  private final InputValidator validator;

  @PostMapping(value = "/event")
  public ResponseEntity<?> publishNumberCollection(
      @Valid @RequestBody String payload) {
    //service.publishNumberCollectionEvent(rawData);
    if(!validator.isValid(payload)) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    RawData rawData = new RawData(payload);
    service.publishNumberCollectionEvent(rawData);
    //computeNumberService.compute(rawData.toEntity());

    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @GetMapping(value = "/stats")
  public String getStatistics() {
    return computeNumberService.getStatistics().getPayload();
  }

}
