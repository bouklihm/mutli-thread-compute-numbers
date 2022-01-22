package com.marou.computenumbers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExpiredTimestampException extends RuntimeException {

  public ExpiredTimestampException() {
    super("timestamp expired");
  }
}
