package com.nhnacademy.shoppingmall.useraddress.service.impl;

import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.useraddress.service.UserAddressService;
import java.util.List;

public class UserAddressServiceImpl implements UserAddressService {
  UserRepository userRepository = new UserRepositoryImpl();
  @Override
  public List<String> getUserAddresses(String userId) {
    return userRepository.getUserAddresses(userId);
  }
}
