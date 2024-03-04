package com.nhnacademy.springjpa.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "household")
@NoArgsConstructor
public class Household {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "household_serial_number")
  private int householdSerialNumber;

  @ManyToOne
  @JoinColumn(name = "household_resident_serial_number")
  private Resident resident;

  @OneToMany(mappedBy = "household", cascade = CascadeType.REMOVE)
  private List<HouseholdMovementAddress> householdMovementAddresses;

  @OneToMany(mappedBy = "household", cascade = CascadeType.REMOVE)
  private List<HouseholdCompositionResident> householdCompositionResidents;

  @Column(name = "household_composition_date")
  private LocalDate householdCompositionDate;
  @Column(name = "household_composition_reason_code", length = 20)
  private String householdCompositionReasonCode;
  @Column(name = "current_house_movement_address", length = 500)
  private String currentHouseMovementAddress;

  @Builder
  public Household(int householdSerialNumber, Resident resident,
      LocalDate householdCompositionDate, String householdCompositionReasonCode,
      String currentHouseMovementAddress) {
    this.householdSerialNumber = householdSerialNumber;
    this.resident = resident;
    this.householdMovementAddresses = new ArrayList<>();
    this.householdCompositionResidents = new ArrayList<>();
    this.householdCompositionDate = householdCompositionDate;
    this.householdCompositionReasonCode = householdCompositionReasonCode;
    this.currentHouseMovementAddress = currentHouseMovementAddress;
  }
}
