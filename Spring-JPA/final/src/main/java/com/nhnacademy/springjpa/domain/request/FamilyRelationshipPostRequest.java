package com.nhnacademy.springjpa.domain.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Setter
public class FamilyRelationshipPostRequest {
  int familySerialNumber;
  @NotBlank
  @NotNull
  String relationShip;
}
