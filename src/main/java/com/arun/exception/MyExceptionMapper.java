package com.arun.exception;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;
import javax.xml.bind.ValidationException;

import org.springframework.stereotype.Component;

@Component
public class MyExceptionMapper {

}
/*public class MyExceptionMapper implements ExceptionMapper<MyException> {

  @Context
  private Providers providers;

  @Override
  public Response toResponse(MyException exception) {
    ExceptionMapper<Exception> mapper
        = providers.getExceptionMapper(Exception.class);
    return mapper.toResponse(exception.getException());
  }
}*/
