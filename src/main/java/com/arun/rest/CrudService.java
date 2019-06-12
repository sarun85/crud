package com.arun.rest;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import com.arun.model.Customer;
import com.arun.bean.CustomerDetails;
import org.springframework.stereotype.Component;

@Component
@Path("/customer")
public class CrudService {

  @Inject
  private CustomerDetails customerDetails;

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
  public void modifyCustomer(Customer customer, @Context HttpServletRequest request){
    customerDetails.modifyCustomer(customer);
  }
}
