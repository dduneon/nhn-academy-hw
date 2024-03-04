package com.nhnacademy.springmvc.repository.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.nhnacademy.springmvc.domain.Role;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.exception.UserAlreadyExistException;
import com.nhnacademy.springmvc.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRepositoryImplTest {
  private UserRepository userRepository;

  @BeforeEach
  public void setUp() {
    userRepository = new UserRepositoryImpl();
  }

  private static User user = new User("testId", "testPassword", "testName", Role.Customer);
  @Test
  void isExist() {
    assertFalse(userRepository.isExist(user.getId()));
    userRepository.save(user);
    assertTrue(userRepository.isExist(user.getId()));
  }

  @Test
  void findById() {
    userRepository.save(user);
    User actual = userRepository.findById(user.getId());

    assertEquals(user, actual);
  }

  @Test
  void save() {
    assertDoesNotThrow(() -> userRepository.save(user));
    assertThrows(UserAlreadyExistException.class, () -> userRepository.save(user));
  }
}