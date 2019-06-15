package com.arun.exceptionmapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Provider
public class WebApplicationExceptionMapper extends AbstractExceptionMapper implements
    javax.ws.rs.ext.ExceptionMapper<WebApplicationException> {

  @Override
  public Response toResponse(WebApplicationException e) {
    ResponseEntity re = new ResponseEntity(HttpStatus.BAD_GATEWAY);

    return this.errorResponse(e.getResponse().getStatus(), re, e);
  }
}
