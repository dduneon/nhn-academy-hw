package com.nhnacademy.account.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "Account")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "balance")
  private Integer balance;

  @Builder
  public Account(String name, Integer balance) {
    this.name = name;
    this.balance = balance;
  }
}
