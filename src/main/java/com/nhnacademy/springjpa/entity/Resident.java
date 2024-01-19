package com.nhnacademy.springjpa.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "resident")
public class Resident {
  private int residentSerialNumber;
  private String name;
  private String residentRegistrationNumber;
  private String genderCode;
  private LocalDateTime birthDate;


}
