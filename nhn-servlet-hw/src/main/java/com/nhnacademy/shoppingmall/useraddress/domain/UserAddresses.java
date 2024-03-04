package com.nhnacademy.shoppingmall.useraddress.domain;

public class UserAddresses {

  // todo 이거 필요한지 고민
  private String userId;
  private String userAddress;

  public UserAddresses(String userId, String userAddress) {
    this.userId = userId;
    this.userAddress = userAddress;
  }

  public String getUserId() {
    return userId;
  }

  public String getUserAddress() {
    return userAddress;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }
}
