package com.nhnacademy.springjpa.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {
  @Id
  @Column(name = "user_id")
  private String userId;
  @Column(name="UserName")
  private String userName;
  @Column(name = "UserPassword")
  private String userPassword;
  @Column(name = "UserBirth")
  private String userBirth;
  @Column(name = "UserAuth")
  @Enumerated(EnumType.STRING)
  private Auth userAuth;
  @Column(name = "UserPoint")
  private int userPoint;
  @Column(name = "user_created")
  private LocalDateTime userCreated;
  @Column(name = "lastest_login_at")
  private LocalDateTime lastestLogin;

  public enum Auth {
    ROLE_ADMIN, ROLE_USER
  }
}
