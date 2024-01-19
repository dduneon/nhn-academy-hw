package com.nhnacademy.springjpa.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "certicifate_issue")
public class CertificateIssue {
  @Id
  @Column(name = "certificate_confirmation_number")
  private long certificateConfirmationNumber;

  @ManyToOne
  @JoinColumn(name="resident_serial_number")
  private Resident resident;

  @Column(name = "certificate_type_code")
  private String certificateTypeCode;

  @Column(name = "certificate_issue_date")
  private LocalDate certificateIssueDate;

}
