package com.nhnacademy.springjpa.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
  @Id
  @Column(name = "user_id")
  private String id;

  @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
  private List<UserAddress> userAddress;
  @Column(name="user_name")
  private String name;
  @Column(name = "user_password")
  private String password;
  @Column(name = "user_birth")
  private String birth;
  @Column(name = "user_auth")
  @Enumerated(EnumType.STRING)
  private Auth auth;
  @Column(name = "user_point")
  private int point;
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @Column(name = "latest_login_at")
  private LocalDateTime latestLogin;

  public enum Auth {
    ROLE_ADMIN, ROLE_USER
  }
}
