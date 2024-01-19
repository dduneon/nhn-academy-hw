package com.nhnacademy.springjpa.entity;

import java.io.Serializable;
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
@Table(name = "family_relationship")
public class FamilyRelationship {
  @EmbeddedId
  private PK pk;

  @Column(name = "family_relationship_code", length = 20)
  private String familyRelationshipCode;

  @ManyToOne
  @MapsId("baseResidentSerialNumber")
  @JoinColumn(name = "base_resident_serial_number")
  private Resident resident;

  @NoArgsConstructor
  @AllArgsConstructor
  @Embeddable
  @EqualsAndHashCode
  @Getter
  public static class PK implements Serializable {
    @Column(name = "family_resident_serial_number")
    private int familyResidentSerialNumber;
    @Column(name = "base_resident_serial_number")
    private int baseResidentSerialNumber;
  }
}
