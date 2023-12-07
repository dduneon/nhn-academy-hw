package com.nhnacademy.shoppingmall.useraddress.service.impl;

import com.nhnacademy.shoppingmall.useraddress.repository.UserAddressRepository;
import com.nhnacademy.shoppingmall.useraddress.repository.impl.UserAddressRepositoryImpl;
import com.nhnacademy.shoppingmall.useraddress.service.UserAddressService;
import java.util.List;

public class UserAddressServiceImpl implements UserAddressService {
  private final UserAddressRepository userAddressRepository;

  public UserAddressServiceImpl(UserAddressRepository userAddressRepository) {
    this.userAddressRepository = userAddressRepository;
  }

  @Override
  public List<String> getUserAddresses(String userId) {
    return userAddressRepository.getUserAddresses(userId);
  }
}
