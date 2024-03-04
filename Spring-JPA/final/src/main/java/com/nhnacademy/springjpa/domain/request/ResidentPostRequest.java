package com.nhnacademy.springjpa.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
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
public class ResidentPostRequest {
  int residentSerialNumber;
  @NotBlank
  @NotNull
  String name;
  @NotBlank
  @NotNull
  String residentRegistrationNumber;
  @NotBlank
  @NotNull
  String genderCode;
  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
  LocalDateTime birthDate;
  @NotBlank
  @NotNull
  String birthPlaceCode;
  @NotBlank
  @NotNull
  String registrationBaseAddress;

  @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
  LocalDateTime deathDate;
  String deathPlaceCode;
  String deathPlaceAddress;
}
