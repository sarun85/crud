package com.arun.exceptionmapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.arun.bean.JacksonFactory;
import com.arun.exception.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Provider
public class ConstraintViolationExceptionMapper extends AbstractExceptionMapper implements
    javax.ws.rs.ext.ExceptionMapper<ConstraintViolationException> {

  @Override
  public Response toResponse(ConstraintViolationException e) {

    List<Map<String, ?>> data = new ArrayList<>();
    Map<String, String> errorMap;
    for (final ConstraintViolation<?> error : e.getConstraintViolations()) {
      errorMap = new HashMap<>();
      errorMap.put("attribute", error.getPropertyPath().toString());
      errorMap.put("message", error.getMessage());
      data.add(errorMap);
    }

    String errorData;
    try {
      errorData = JacksonFactory.createJson(data);
    } catch (JsonParseException jpe) {
      errorData = "Temporary Error";
    }

    ResponseEntity re = new ResponseEntity(errorData, HttpStatus.BAD_REQUEST);

    return this.errorResponse(HttpStatus.BAD_REQUEST.value(), re, e);
  }
}
