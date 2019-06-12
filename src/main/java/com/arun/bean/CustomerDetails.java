package com.arun.bean;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import com.arun.dao.CustomerDao;
import com.arun.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDetails {

  @Inject
  private CustomerDao customerDao;

  public List<Customer> getCustomerDetails() {
    return customerDao.getCustomers();
  }

  public Customer getCustomer(Long accountNo) {
    return customerDao.getCustomer(accountNo).orElseThrow(() -> new RuntimeException("Customer not found"));
  }

  public void addCustomer(Customer customer) {
    customerDao.addCustomer(customer);
  }

  public void modifyCustomer(Customer customer) {
    customerDao.modifyCustomer(customer);
  }
}
