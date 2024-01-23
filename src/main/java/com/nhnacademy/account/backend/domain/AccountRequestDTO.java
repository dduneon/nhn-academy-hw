package com.nhnacademy.account.backend.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AccountRequestDTO {
  @NotNull
  @NotEmpty
  private String number;
  @NotNull
  private Integer balance;
}
