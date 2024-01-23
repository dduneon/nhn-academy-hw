package com.nhnacademy.account.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "Account")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private Long id;
  @Column(name = "number")
  private String number;
  @Column(name = "balance")
  private Integer balance;

  @Builder
  public Account(String number, Integer balance) {
    this.number = number;
    this.balance = balance;
  }
}
