package com.nhnacademy.springjpa.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "resident")
public class Resident {
  @Id
  @Column(name = "resident_serial_number")
  private int residentSerialNumber;

  @OneToMany(mappedBy = "resident")
  private List<CertificateIssue> certificateIssues;

  @OneToMany(mappedBy = "resident")
  private List<BirthDeathReportResident> birthDeathReportResidents;

  @Column(name = "name", length = 100)
  private String name;
  @Column(name = "resident_registration_number", length = 300)
  private String residentRegistrationNumber;
  @Column(name = "gender_code", length = 20)
  private String genderCode;
  @Column(name = "birth_date")
  private LocalDateTime birthDate;
  @Column(name = "birth_place_code", length = 20)
  private String birthPlaceCode;
  @Column(name = "registration_base_address", length = 500)
  private String registrationBaseAddress;
  @Column(name = "death_date")
  private LocalDateTime deathDate;
  @Column(name = "death_place_code", length = 20)
  private String deathPlaceCode;
  @Column(name = "death_place_address", length = 500)
  private String deathPlaceAddress;

}
