package com.nhnacademy.edu.springframework.project.service.impl;

import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvStudents;
import com.nhnacademy.edu.springframework.project.domain.Score;

import com.nhnacademy.edu.springframework.project.service.GradeQueryService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultGradeQueryService implements GradeQueryService {
    private final Students students;

    @Autowired
    public DefaultGradeQueryService(Students students) {
        this.students = students;
    }

    @Override
    public List<Score> getScoreByStudentName(String name) {
        // TODO 5: 학생 이름으로 점수를 반환합니다. 동명이인 고려합니다.
        return students.findAll().stream().filter(student -> Objects.nonNull(student.getScore())).filter(student -> student.getName().equals(name)).map(
            Student::getScore).collect(
            Collectors.toList());
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        // TODO 6 : 학번으로 점수를 반환합니다. seq 인자가 학번입니다.
        return students.findAll().stream().filter(student -> Objects.nonNull(student.getScore()))
            .filter(student -> student.getSeq() == seq).map(Student::getScore).findFirst()
            .orElse(null);
    }
}
