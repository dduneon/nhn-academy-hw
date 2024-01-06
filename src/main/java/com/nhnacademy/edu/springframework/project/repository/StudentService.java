package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.Student;

import java.util.Collection;

public interface StudentService {
    Collection<Student> getPassedStudents();

    Collection<Student> getStudentsOrderByScore();
}
