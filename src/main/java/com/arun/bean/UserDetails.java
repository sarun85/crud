package com.arun.bean;

import java.util.Arrays;
import java.util.List;

import com.arun.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDetails {

  public List<User> getUserDetails() {
    User user1 = User.of(1L, "ArunSundaram", "Sekar", "Hyd", "12345");
    User user2 = User.of(2L, "Archana", "T", "Mas", "09823");
    User user3 = User.of(3L, "Arjun", "A", "Cbe", "13555");
    return Arrays.asList(user1, user2, user3);
  }
}
