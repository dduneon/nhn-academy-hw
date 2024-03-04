package com.nhnacademy.account.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Account")
public class Account {
  @Id
  @Column(name="id")
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "balance")
  private Integer balance;
}
