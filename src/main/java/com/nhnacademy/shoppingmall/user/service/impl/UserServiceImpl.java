package com.nhnacademy.shoppingmall.user.service.impl;

import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.user.service.UserService;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User getUser(String userId) {
    //todo#4-1 회원조회
    Optional<User> user = userRepository.findById(userId);
    return user.orElse(null);
  }

  @Override
  public void saveUser(User user) {
    //todo#4-2 회원등록
    if (Objects.isNull(user)) {
      throw new RuntimeException("user is null");
    }
    if (Objects.nonNull(getUser(user.getUserId()))) {
      throw new UserAlreadyExistsException("user already exist");
    }
    int result = userRepository.save(user);
    if (result < 1) {
      throw new RuntimeException("Do not save user");
    }
  }

  @Override
  public void updateUser(User user) {
    //todo#4-3 회원수정
    if (Objects.isNull(user)) {
      throw new RuntimeException("user is null");
    }
    if (Objects.isNull(getUser(user.getUserId()))) {
      throw new UserNotFoundException("user doesn't exist");
    }
    int result = userRepository.update(user);
    if (result < 1) {
      throw new RuntimeException("Do not update user");
    }
  }

  @Override
  public void deleteUser(String userId) {
    //todo#4-4 회원삭제
    if (Objects.nonNull(getUser(userId))) {
      throw new UserNotFoundException("user doesn't exist");
    }
    int result = userRepository.deleteByUserId(userId);
    if (result < 1) {
      throw new RuntimeException("Do not update user");
    }
  }

  @Override
  public User doLogin(String userId, String userPassword) {
    //todo#4-5 로그인 구현, userId, userPassword로 일치하는 회원 조회
    Optional<User> user = userRepository.findByUserIdAndUserPassword(userId, userPassword);
    if (user.isEmpty() || Objects.isNull(user)) {
      throw new UserNotFoundException("user doesn't exist");
    }
    // 최근 로그인 시간을 현재 시간으로 변경
    userRepository.updateLatestLoginAtByUserId(userId, LocalDateTime.now());
    return user.get();
  }

}
