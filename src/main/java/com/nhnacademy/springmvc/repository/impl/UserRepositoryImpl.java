package com.nhnacademy.springmvc.repository.impl;

import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.exception.UserAlreadyExistException;
import com.nhnacademy.springmvc.repository.UserRepository;
import java.util.HashMap;
import java.util.Objects;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
  private final HashMap<String, User> userMap = new HashMap<>();

  @Override
  public boolean isExist(String id) {
    return userMap.containsKey(id);
  }

  @Override
  public User findById(String id) {
    return isExist(id) ? userMap.get(id) : null;
  }

  @Override
  public void save(User user) {
    if(isExist(user.getId()))
      throw new UserAlreadyExistException();

    userMap.put(user.getId(), user);
  }
}
