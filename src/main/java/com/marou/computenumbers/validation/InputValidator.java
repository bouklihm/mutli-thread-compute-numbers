package com.marou.computenumbers.validation;

import java.math.BigDecimal;
import java.time.Instant;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
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
      throw new ValidationException();
    }
    return true;
  }

  private boolean isInputComplete(String input) {
    String[] splitData = input.split(",");
    if (splitData.length != 3) {
      throw new ValidationException();
    }
    return true;
  }

  private boolean isTimestampValid(String input) {
    String[] splitData = input.split(",");
    try {
      Instant.ofEpochMilli(Long.parseLong(splitData[0]));
      return true;
    } catch (ValidationException e) {
      return false;
    }
  }

  private boolean isXValid(String input) {
    String[] splitData = input.split(",");
    try {
      Double.parseDouble(splitData[1]);
    } catch (ValidationException e) {
      return false;
    }

    if (BigDecimal.valueOf(Double.parseDouble(splitData[1])).scale() > 10) {
      throw new ValidationException();
    }
    return false;
  }

  private boolean isYValid(String input) {
    String[] splitData = input.split(",");
    try {
      Integer.parseInt(splitData[2]);
      return true;
    } catch (ValidationException e) {
      return false;
    }
  }

}
