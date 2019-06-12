package com.arun.model;

import java.util.Objects;

public class User {

  private Long accountNo;
  private String firstName;
  private String lastName;
  private String city;
  private String zip;
  
  private User() {
  }
  
  private User(Long accountNo, String firstName, String lastName, String city, String zip) {
    super();
    this.accountNo = accountNo;
    this.firstName = firstName;
    this.lastName = lastName;
    this.city = city;
    this.zip = zip;
  }
  
  public static User of(Long accountNo, String firstName, String lastName, String city, String zip) {
    return new User(accountNo, firstName, lastName, city, zip);
  }

  public Long getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(Long accountNo) {
    this.accountNo = accountNo;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountNo);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    return Objects.equals(accountNo, other.accountNo);
  }
}
