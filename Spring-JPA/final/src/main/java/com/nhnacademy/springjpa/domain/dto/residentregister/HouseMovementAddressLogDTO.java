package com.nhnacademy.springjpa.domain.dto.residentregister;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HouseMovementAddressLogDTO {
  private final String houseMovementAddress;
  private final LocalDate houseMovementReportDate;
}
