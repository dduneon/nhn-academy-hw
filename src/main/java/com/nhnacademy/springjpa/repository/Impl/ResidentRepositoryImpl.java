package com.nhnacademy.springjpa.repository.Impl;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import com.nhnacademy.springjpa.domain.IssuableResidentDTO;
import com.nhnacademy.springjpa.entity.QBirthDeathReportResident;
import com.nhnacademy.springjpa.entity.QCertificateIssue;
import com.nhnacademy.springjpa.entity.QResident;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.repository.ResidentRepositoryCustom;
import com.querydsl.core.types.Projections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ResidentRepositoryImpl extends QuerydslRepositorySupport implements
    ResidentRepositoryCustom {

  public ResidentRepositoryImpl() {
    super(Resident.class);
  }

  public List<IssuableResidentDTO> findResidentInfoWithBirthDeathAndCertificate() {
    QResident resident = QResident.resident;
    QCertificateIssue certificateIssue = QCertificateIssue.certificateIssue;
    QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;

    return from(resident)
        .leftJoin(resident.certificateIssues, certificateIssue)
        .leftJoin(resident.birthDeathReportResidents, birthDeathReportResident)
        .select(
            Projections.fields(
                IssuableResidentDTO.class,
                resident.residentSerialNumber,
                resident.name
            ),
            Projections.fields(
                IssuableResidentDTO.BirthDeathReportResidentDTO.class,
                birthDeathReportResident.pk.birthDeathTypeCode
            ).as("birthDeathReportResident"),
            Projections.fields(
                IssuableResidentDTO.CertificateIssueDTO.class,
                certificateIssue.certificateConfirmationNumber
            ).as("certificateIssue")
        )
        .transform(
            groupBy(resident.residentSerialNumber).list(
                Projections.fields(
                    IssuableResidentDTO.class,
                    resident.residentSerialNumber,
                    resident.name,
                    list(
                        Projections.fields(
                            IssuableResidentDTO.BirthDeathReportResidentDTO.class,
                            birthDeathReportResident.pk.birthDeathTypeCode
                        )
                    ).as("birthDeathReportResident"),
                    list(
                        Projections.fields(
                            IssuableResidentDTO.CertificateIssueDTO.class,
                            certificateIssue.certificateConfirmationNumber
                        )
                    ).as("certificateIssue")
                )
            )
        )
        .stream()
        .map(residentDTO -> {
          // birthDeathReportResident가 null이 아닌 경우에만 filter
          if (residentDTO.getBirthDeathReportResident() != null) {
            residentDTO.setBirthDeathReportResident(
                residentDTO.getBirthDeathReportResident().stream()
                    .filter(birthDeathReportResidentDTO -> birthDeathReportResidentDTO.getBirthDeathTypeCode() != null)
                    .collect(Collectors.toList())
            );
          }
          if (residentDTO.getCertificateIssue() != null) {
            residentDTO.setCertificateIssue(
                residentDTO.getCertificateIssue().stream()
                    .filter(certificateIssueDTO -> certificateIssueDTO.getCertificateConfirmationNumber() != null)
                    .collect(Collectors.toList())
            );
          }
          return residentDTO;
        })
        .collect(Collectors.toList());
  }
}
