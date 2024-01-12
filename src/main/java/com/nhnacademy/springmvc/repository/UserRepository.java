package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.User;

public interface UserRepository {
  boolean isExist(String id);
  User findById(String id);
  void save(User user);
}
