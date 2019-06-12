package com.arun.rest;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.arun.model.User;
import com.arun.bean.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Path("/")
public class CrudService {

  @Inject
  private UserDetails userDetails;

  @GET
  @Produces("application/json")
  public String sayHelloWorld() {
    return "Hello World" + LocalDate.now();
  }

  @Path("users")
  @GET
  @Produces("application/json")
  public List<User> getUserDetails() {
    return userDetails.getUserDetails();
  }
}
