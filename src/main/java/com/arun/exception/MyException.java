package com.arun.exception;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.WebApplicationException;

import org.springframework.stereotype.Component;

public class MyException extends WebApplicationException {

  private final Exception e;

  public MyException(Exception e) {
    this.e = e;
  }

  public Exception getException() {
    return e;
  }
}
