package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.PointRecord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRecordRepository extends JpaRepository<PointRecord, Long> {
  List<PointRecord> findByIdAndUser_Id(Long id, String userId);
}
