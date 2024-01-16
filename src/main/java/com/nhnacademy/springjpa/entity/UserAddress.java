package com.nhnacademy.springjpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserAddress")
public class UserAddress {
  @Id
  @Column(name = "user_id")
  private String userId;
  @Column(name = "user_address")
  private String userAddress;
}
