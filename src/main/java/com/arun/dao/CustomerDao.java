package com.arun.dao;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import com.arun.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {

  @Inject
  private JdbcTemplate jdbcTemplate;

  public static final RowMapper<Customer> customerRowMapper = (rs, rowNum) -> Customer.of(rs.getLong("account_no"), rs.getString("first_name"), rs.getString("last_name"),
      rs.getString("city"), rs.getString("zip"));

  public void addCustomer(Customer customer) {
    jdbcTemplate.update("insert into customers values(first_name, last_name, city, zip, account_no) VALUES (?,?,?,?,?)", customer.toObjArr());
  }

  public Optional<Customer> getCustomer(Long accountNo) {
    return jdbcTemplate.query("select * from customers where account_no=?", new Object[] {accountNo}, customerRowMapper).stream().findFirst();
  }

  public List<Customer> getCustomers() {
    return jdbcTemplate.query("select * from customers", customerRowMapper);
  }

  public void modifyCustomer(Customer customer) {
    jdbcTemplate.update("update customers set first_name=?, last_name=?, city=?, zip=? where account_no=?", customer.toObjArr());
  }




}
