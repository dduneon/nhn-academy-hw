package com.nhnacademy.springjpa.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "certificate_issue")
public class CertificateIssue {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "certificate_confirmation_number")
  private long certificateConfirmationNumber;

  @ManyToOne
  @JoinColumn(name="resident_serial_number")
  private Resident resident;

  @Column(name = "certificate_type_code")
  private String certificateTypeCode;

  @Column(name = "certificate_issue_date")
  private LocalDate certificateIssueDate;

  public CertificateIssue(Resident resident, String certificateTypeCode) {
    this.resident = resident;
    this.certificateTypeCode = certificateTypeCode;
    this.certificateIssueDate = LocalDate.now();
  }
}
