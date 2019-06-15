package com.arun.exceptionmapper;

import javax.ws.rs.core.Response;

import com.arun.CrudApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public abstract class AbstractExceptionMapper {
  private static final org.slf4j.Logger log = LoggerFactory.getLogger(AbstractExceptionMapper.class);

  protected Response errorResponse(int status, ResponseEntity responseEntity) {
    return customizeResponse(status, responseEntity);
  }

  protected Response errorResponse(int status, ResponseEntity responseEntity, Throwable t) {
    log.error(t.getLocalizedMessage());
    return customizeResponse(status, responseEntity);
  }

  private Response customizeResponse(int status, ResponseEntity responseEntity) {
    return Response.status(status).entity(responseEntity).build();
  }
}
