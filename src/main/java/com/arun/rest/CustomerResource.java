package com.arun.rest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.arun.model.Customer;
import com.arun.bean.CustomerDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@Path("/customer")
public class CustomerResource {

  @Inject
  private CustomerDetails customerDetails;

  private static Validator validator;

  @PostConstruct
  public void init(){
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Path("{accountNo}")
  @GET
  @Produces("application/json")
  public Customer sayHelloWorld(@PathParam("accountNo") Long accountNo) {
    return customerDetails.getCustomer(accountNo);
  }

  @Path("all")
  @GET
  @Produces("application/json")
  public List<Customer> getCustomerDetails() {
    return customerDetails.getCustomerDetails();
  }

  @Path("modify")
  @PUT
  @Consumes("application/json")
  @Produces("application/json")
  public Response modifyCustomer(Customer customer, @Context HttpServletRequest request){
    Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(
          new HashSet<ConstraintViolation<?>>(violations));
    }
    customerDetails.modifyCustomer(customer);
    return Response.ok().build();
  }

  @Path("add")
  @POST
  @Consumes("application/json")
  @Produces("application/json")
  public Response addCustomer(Customer customer, @Context HttpServletRequest request){
    Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(
          new HashSet<ConstraintViolation<?>>(violations));
    }
    customerDetails.addCustomer(customer);
    return Response.ok().build();
  }

  @Path("{accountNo}")
  @DELETE
  public Response deleteCustomer(@PathParam("accountNo") Long accountNo) {
    customerDetails.deleteCustomer(accountNo);
    return Response.ok().build();
  }
}
