package com.nhnacademy.springjpa.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "resident")
public class Resident {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "resident_serial_number")
  private int residentSerialNumber;

  @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
  private List<CertificateIssue> certificateIssues;

  @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
  private List<BirthDeathReportResident> birthDeathReportResidents;

  @OneToMany(mappedBy = "reportResident", cascade = CascadeType.REMOVE)
  private List<BirthDeathReportResident> reportBirthDeathReportResidents;

  @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
  private List<HouseholdCompositionResident> householdCompositionResidents;

  @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
  private List<Household> households;

  @OneToMany(mappedBy = "baseResident", cascade = CascadeType.REMOVE)
  private List<FamilyRelationship> baseFamilyRelationships;

  @OneToMany(mappedBy = "familyResident", cascade = CascadeType.REMOVE)
  private List<FamilyRelationship> familyRelationships;

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

  @Builder
  public Resident(int residentSerialNumber, String name, String residentRegistrationNumber, String genderCode,
      LocalDateTime birthDate, String birthPlaceCode, String registrationBaseAddress,
      LocalDateTime deathDate, String deathPlaceCode, String deathPlaceAddress) {
    this.residentSerialNumber = residentSerialNumber;
    this.name = name;
    this.residentRegistrationNumber = residentRegistrationNumber;
    this.genderCode = genderCode;
    this.birthDate = birthDate;
    this.birthPlaceCode = birthPlaceCode;
    this.registrationBaseAddress = registrationBaseAddress;
    this.deathDate = deathDate;
    this.deathPlaceCode = deathPlaceCode;
    this.deathPlaceAddress = deathPlaceAddress;
  }

}
