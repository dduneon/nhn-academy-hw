package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvStudents;
import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.service.impl.CsvDataLoadService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DataLoadServiceTest {
    @InjectMocks
    private DataLoadService dataLoadService;

//    @Mock
//    Scores scores;

    @Mock
    Students students;

    private List<Student> expected;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);

        Student student1 = new Student(1, "A");
        student1.setScore(new Score(1, 30));
        Student student2 = new Student(2, "B");
        student2.setScore(new Score(2, 80));
        Student student3 = new Student(3, "A");
        student3.setScore(new Score(3, 70));
        Student student4 = new Student(4, "D");

        expected = List.of(
            student1, student2, student3, student4
        );
    }

    @Test
    void loadAndMerge() {
        dataLoadService.loadAndMerge();
        List<Student> actual = (List<Student>) students.findAll();
        for(int i=0; i<expected.size(); i++) {
            Score expectedScore = expected.get(i).getScore();
            Score actualScore = actual.get(i).getScore();
            Assertions.assertEquals(Objects.isNull(expectedScore), Objects.isNull(actualScore));
            if (Objects.nonNull(expectedScore) && Objects.nonNull(actualScore)) {
                Assertions.assertEquals(expected.get(i).getSeq(), actual.get(i).getSeq());
                Assertions.assertEquals(expected.get(i).getName(), actual.get(i).getName());
                Assertions.assertEquals(expectedScore.getScore(), actualScore.getScore());
            }
        }
    }
}