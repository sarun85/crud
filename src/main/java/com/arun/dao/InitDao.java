package com.arun.dao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.arun.CrudApplication;
import com.arun.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InitDao implements CommandLineRunner {

  @Inject
  private JdbcTemplate jdbcTemplate;

  private static final Logger log = LoggerFactory.getLogger(CrudApplication.class);

  @Override
  public void run(String... args) throws Exception {
    log.info("Creating tables");
    jdbcTemplate.execute("DROP TABLE customers IF EXISTS");

    jdbcTemplate.execute("CREATE TABLE customers(" +
        "account_no VARCHAR2(10), first_name VARCHAR2(50), last_name VARCHAR2(50), city VARCHAR2(50), zip NUMBER(5))");

    // Split up the array of whole names into an array of first/last names
    List<Object[]> splitUpNames = Arrays.asList("1 John Woo London 12345",
        "2 Jeff Dean Ireland 23456",
        "3 Josh Bloch Boston 32400",
        "4 Josh Long NewYork 89746").stream()
        .map(name -> name.split(" "))
        .collect(Collectors.toList());

    // Use a Java 8 stream to print out each tuple of the list
    splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

    // Uses JdbcTemplate's batchUpdate operation to bulk load data
    jdbcTemplate.batchUpdate("INSERT INTO customers(account_no, first_name, last_name, city, zip) VALUES (?,?,?,?,?)", splitUpNames);

    log.info("Querying for customer records where first_name = 'Josh':");
    jdbcTemplate.query(
        "SELECT account_no, first_name, last_name, city, zip FROM customers WHERE first_name = ?", new Object[] { "Josh" },
        (rs, rowNum) -> Customer.of(rs.getLong("account_no"), rs.getString("first_name"), rs.getString("last_name"),
            rs.getString("city"), rs.getString("zip"))
    ).forEach(customer -> log.info(customer.toString()));
  }
}
