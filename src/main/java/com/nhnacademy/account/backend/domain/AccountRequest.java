package com.nhnacademy.account.backend.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AccountRequest {
  @NotNull
  private Long id;
  @NotNull
  @NotEmpty
  private String name;
  @NotNull
  private Integer balance;
}
