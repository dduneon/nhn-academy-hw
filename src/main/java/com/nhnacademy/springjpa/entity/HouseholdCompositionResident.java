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
@Table(name = "household_composition_resident")
public class HouseholdCompositionResident {
  @EmbeddedId
  private PK pk;

  @ManyToOne
  @MapsId("householdSerialNumber")
  @JoinColumn(name = "household_serial_number")
  private Household household;

  @ManyToOne
  @MapsId("residentSerialNumber")
  @JoinColumn(name = "resident_serial_number")
  private Resident resident;

  @Column(name = "report_date")
  private LocalDate reportDate;
  @Column(name = "household_relationship_code", length = 20)
  private String householdRelationshipCode;
  @Column(name = "household_composition_change_reason_code", length = 20)
  private String householdCompositionChangeReasonCode;

  @Embeddable
  @NoArgsConstructor
  @AllArgsConstructor
  @EqualsAndHashCode
  @Getter
  public static class PK implements Serializable {
    @Column(name = "household_serial_number")
    private int householdSerialNumber;
    @Column(name = "resident_serial_number")
    private int residentSerialNumber;
  }
}
