package com.marou.computenumbers.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.marou.computenumbers.ComputeNumbersApplication;
import javax.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ComputeNumbersApplication.class)
@AutoConfigureMockMvc
class InputValidatorTest {

  public static final String VALID_INPUT = "1607341341814,0.0442672968,1282509067";
  public static final String EMPTY_INPUT = "";
  public static final String WRONG_SIZE_INPUT = "1607341341814,0.0442672968";
  public static final String BAD_TIMESTAMP_INPUT = "9.9,0.0442672968,1282509067";
  public static final String LONG_X_INPUT = "1607341341814,0.04426729681111,1282509067";
  public static final String BAD_Y_INPUT = "1607341341814,0.0442672968,x";
  @InjectMocks
  private InputValidator validator;

  @Test
  public void shouldReturnTrueForValidInput() {
    boolean result = validator.isValid(VALID_INPUT);

    assertTrue(result);
  }

  @Test
  public void shouldThrowValidationExceptionForEmptyInput() {

    ValidationException thrownException = assertThrows(ValidationException.class,
        () -> validator.isValid(EMPTY_INPUT));

    assertThrows(ValidationException.class, () -> validator.isValid(EMPTY_INPUT));

    assertEquals("input is empty", thrownException.getMessage());
  }

  @Test
  public void shouldThrowValidationExceptionForUnexpectedInputSize() {

    ValidationException thrownException = assertThrows(ValidationException.class,
        () -> validator.isValid(WRONG_SIZE_INPUT));

    assertThrows(ValidationException.class, () -> validator.isValid(WRONG_SIZE_INPUT));

    assertEquals("Not the right amounts of values expected", thrownException.getMessage());
  }

  @Test
  public void shouldThrowValidationExceptionForInvalidTimeStamp() {

    ValidationException thrownException = assertThrows(ValidationException.class,
        () -> validator.isValid(BAD_TIMESTAMP_INPUT));

    assertThrows(ValidationException.class, () -> validator.isValid(BAD_TIMESTAMP_INPUT));

    assertEquals("Timestamp value is not valid", thrownException.getMessage());
  }

  @Test
  public void shouldThrowValidationExceptionForInvalidXValue() {

    ValidationException thrownException = assertThrows(ValidationException.class,
        () -> validator.isValid(LONG_X_INPUT));

    assertThrows(ValidationException.class, () -> validator.isValid(LONG_X_INPUT));

    assertEquals("The X value is too big", thrownException.getMessage());

  }

  @Test
  public void shouldThrowValidationExceptionForInvalidYValue() {

    ValidationException thrownException = assertThrows(ValidationException.class,
        () -> validator.isValid(BAD_Y_INPUT));

    assertThrows(ValidationException.class, () -> validator.isValid(BAD_Y_INPUT));

    assertEquals("The Y value is an Integer", thrownException.getMessage());

  }

}
