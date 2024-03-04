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
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
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
  @Setter
  private String lastAddressYn;

  @Embeddable
  @NoArgsConstructor
  @AllArgsConstructor
  @EqualsAndHashCode
  @Getter
  public static class PK implements Serializable {
    @Column(name = "house_movement_report_date")
    private LocalDate houseMovementReportDate;
    @Column(name = "household_serial_number")
    private int householdSerialNumber;
  }

  @Builder
  public HouseholdMovementAddress(PK pk, Household household, String houseMovementAddress,
      String lastAddressYn) {
    this.pk = pk;
    this.household = household;
    this.houseMovementAddress = houseMovementAddress;
    this.lastAddressYn = lastAddressYn;
  }
}
