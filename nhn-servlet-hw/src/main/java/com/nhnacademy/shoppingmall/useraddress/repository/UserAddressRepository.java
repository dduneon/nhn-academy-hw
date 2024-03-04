package com.nhnacademy.shoppingmall.useraddress.repository;

import java.util.List;

public interface UserAddressRepository {
  public List<String> getUserAddresses(String userId);
}
