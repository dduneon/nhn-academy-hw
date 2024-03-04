package com.nhnacademy.student.service.Impl;

import com.nhnacademy.student.domain.StudentRegisterRequest;
import com.nhnacademy.student.entity.Student;
import com.nhnacademy.student.exception.StudentAlreadyExistException;
import com.nhnacademy.student.exception.StudentNotFoundException;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.service.StudentManageService;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class StudentManageServiceImpl implements StudentManageService {
  private final StudentRepository studentRepository;

  public StudentManageServiceImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public Student getStudent(Long id) {
    Optional<Student> student = studentRepository.findById(id);
    if(Objects.isNull(student) || student.isEmpty())
      throw new StudentNotFoundException("해당하는 학생이 존재하지 않습니다");
    return student.get();
  }

  @Override
  public Student register(StudentRegisterRequest request) {
    return studentRepository.save(Student.builder()
        .name(request.getName())
        .email(request.getEmail())
        .score(request.getScore())
        .comment(request.getComment()).build());
  }

  @Override
  public Student modify(Long studentId, StudentRegisterRequest request) {
    if(!studentRepository.existsById(studentId)) {
      throw new StudentAlreadyExistException("존재하지 않는 아이디입니다");
    }
    return studentRepository.save(new Student(studentId, request.getName(), request.getEmail(), request.getScore(),
        request.getComment()));
  }
}
