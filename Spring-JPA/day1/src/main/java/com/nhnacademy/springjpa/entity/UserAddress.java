package com.nhnacademy.springjpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserAddresses")
public class UserAddress {
  @Id
  @Column(name = "user_id")
  private String id;
  @Column(name = "user_address")
  private String address;
}
