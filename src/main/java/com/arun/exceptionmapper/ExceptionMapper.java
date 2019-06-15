package com.arun.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Provider
public class ExceptionMapper extends AbstractExceptionMapper implements
    javax.ws.rs.ext.ExceptionMapper<Exception> {

  @Override
  public Response toResponse(Exception e) {
    // ResponseEntity class's Member Integer code, String message, Object data. For response format.
    ResponseEntity re = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    return this.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), re, e);
  }
}
