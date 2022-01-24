package com.marou.computenumbers.validation;

import java.math.BigDecimal;
import java.time.Instant;
import javax.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class InputValidator {

  public boolean isValid(String input) {

    return
        isInputPresent(input) &&
            isInputComplete(input) &&
            isTimestampValid(input) &&
            isXValid(input) &&
            isYValid(input);
  }

  private boolean isInputPresent(String input) {
    if (input.isEmpty()) {
      throw new ValidationException("input is empty");
    }
    return true;
  }

  private boolean isInputComplete(String input) {
    String[] splitData = input.split(",");
    if (splitData.length != 3) {
      throw new ValidationException("Not the right amounts of values expected");
    }
    return true;
  }

  private boolean isTimestampValid(String input) {
    String[] splitData = input.split(",");
    try {
      Instant.ofEpochMilli(Long.parseLong(splitData[0]));
      return true;
    } catch (Exception e) {
      throw new ValidationException("Timestamp value is not valid");
    }
  }

  private boolean isXValid(String input) {
    String[] splitData = input.split(",");
    try {
      Double.parseDouble(splitData[1]);
    } catch (Exception e) {
      throw new ValidationException("The X value is not a double");
    }

    if (BigDecimal.valueOf(Double.parseDouble(splitData[1])).scale() > 10) {
      throw new ValidationException("The X value is too big");
    }
    return true;
  }

  private boolean isYValid(String input) {
    String[] splitData = input.split(",");
    try {
      Integer.parseInt(splitData[2]);
      return true;
    } catch (Exception e) {
      throw new ValidationException("The Y value is an Integer");
    }
  }

}
