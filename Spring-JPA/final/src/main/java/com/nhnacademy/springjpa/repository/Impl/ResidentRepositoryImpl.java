package com.nhnacademy.springjpa.repository.Impl;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import com.nhnacademy.springjpa.domain.dto.resident.IssuableResidentDTO;
import com.nhnacademy.springjpa.entity.QBirthDeathReportResident;
import com.nhnacademy.springjpa.entity.QCertificateIssue;
import com.nhnacademy.springjpa.entity.QHouseholdCompositionResident;
import com.nhnacademy.springjpa.entity.QResident;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.repository.custom.ResidentRepositoryCustom;
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
    QHouseholdCompositionResident householdCompositionResident = QHouseholdCompositionResident.householdCompositionResident;

    return from(resident)
        .leftJoin(resident.certificateIssues, certificateIssue)
        .leftJoin(resident.birthDeathReportResidents, birthDeathReportResident)
        .leftJoin(resident.householdCompositionResidents, householdCompositionResident)
        .select(
            Projections.fields(
                IssuableResidentDTO.class,
                resident.residentSerialNumber,
                resident.name,
                householdCompositionResident.pk.householdSerialNumber
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
                    householdCompositionResident.pk.householdSerialNumber,
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
