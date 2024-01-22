package com.nhnacademy.student.service;

import com.nhnacademy.student.domain.StudentRegisterRequest;
import com.nhnacademy.student.entity.Student;

public interface StudentManageService {
  Student getStudent(Long id);
  Student register(StudentRegisterRequest request);
  Student modify(Long studentId, StudentRegisterRequest request);
}
