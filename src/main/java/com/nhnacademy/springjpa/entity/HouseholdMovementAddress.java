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
@Table(name = "household_movement_address")
public class HouseholdMovementAddress {
  @EmbeddedId
  private PK pk;

  @ManyToOne
  @MapsId("householdSerialNumber")
  @JoinColumn(name = "household_serial_number")
  private Household household;

  @Column(name = "house_movement_address")
  private String houseMovementAddress;
  @Column(name = "last_address_yn")
  private String lastAddressYn;

  @Embeddable
  @NoArgsConstructor
  @AllArgsConstructor
  @EqualsAndHashCode
  @Getter
  public class PK implements Serializable {
    @Column(name = "house_movement_report_date")
    private LocalDate houseMovementReportDate;
    @Column(name = "household_serial_number")
    private int householdSerialNumber;
  }
}
