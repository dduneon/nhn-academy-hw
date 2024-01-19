package com.nhnacademy.springjpa.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "birth_death_report_resident")
public class BirthDeathReportResident {
  @EmbeddedId
  private PK pk;

  @ManyToOne
  @MapsId("residentSerialNumber")
  @JoinColumn(name="resident_serial_number")
  private Resident resident;

  @Column(name = "birth_death_report_date")
  private LocalDate birthDeathReportDate;
  @Column(name = "birth_report_qualifications_code", length = 20)
  private String birthReportQualificationsCode;
  @Column(name = "death_report_qualifications_code", length = 20)
  private String deathReportQualificationsCode;
  @Column(name = "email_address", length = 50)
  private String emailAddress;
  @Column(name = "phone_number", length = 20)
  private String phoneNumber;

  @Embeddable
  @NoArgsConstructor
  @AllArgsConstructor
  @Getter
  @EqualsAndHashCode
  public static class PK implements Serializable {
    @Column(name = "birth_death_type_code", length = 2)
    private String birthDeathTypeCode;

    @Column(name = "report_resident_serial_number")
    private int reportResidentSerialNumber;

    @Column(name = "resident_serial_number")
    private int residentSerialNumber;
  }
}
