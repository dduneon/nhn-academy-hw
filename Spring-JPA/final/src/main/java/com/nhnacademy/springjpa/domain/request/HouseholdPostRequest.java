package com.nhnacademy.springjpa.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class HouseholdPostRequest {
  @NotNull
  Integer householdSerialNumber;
  @NotNull
  Integer householdResidentSerialNumber;
  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd")
  LocalDate householdCompositionDate;
  @NotNull
  @NotEmpty
  String householdCompositionReasonCode;
  @NotNull
  @NotEmpty
  String currentHouseMovementAddress;
}
