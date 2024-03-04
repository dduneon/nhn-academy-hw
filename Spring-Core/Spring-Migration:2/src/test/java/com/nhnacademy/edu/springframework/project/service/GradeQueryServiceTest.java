package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.ComponentConfig;
import com.nhnacademy.edu.springframework.project.config.ServiceConfig;
import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.service.impl.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.service.impl.DefaultGradeQueryService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServiceConfig.class, ComponentConfig.class})
class GradeQueryServiceTest {
    @InjectMocks
    private DefaultGradeQueryService gradeQueryService;
    @Mock
    private Students students;

    @Autowired
    private DataLoadService dataLoadService;

    private List<Student> studentList;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        dataLoadService.loadAndMerge();

        Student student1 = new Student(1, "A");
        Student student2 = new Student(2, "B");
        Student student3 = new Student(3, "A");
        Student student4 = new Student(4, "D");
        studentList = List.of(
            student1, student2, student3, student4
        );

        student1.setScore(new Score(1, 30));
        student2.setScore(new Score(2, 80));
        student3.setScore(new Score(3, 70));
    }

    @Test
    void getScoreByStudentName() {
        List<List<Score>> expected = List.of(
            List.of(new Score(1, 30), new Score(3, 70)),
            List.of(new Score(2, 80)),
            List.of(),
            List.of()
        );
        String[] testNames = {"A", "B", "C", "D"};
        Mockito.when(students.findAll()).thenReturn(studentList);
        for(int testCase=0; testCase<studentList.size(); testCase++) {
            List<Score> expectedScoreList = expected.get(testCase);
            List<Score> actualScoreList = gradeQueryService.getScoreByStudentName(testNames[testCase]);

            Assertions.assertEquals(expectedScoreList.size(), actualScoreList.size());
            for(int i=0; i<expectedScoreList.size(); i++) {
                Assertions.assertEquals(expectedScoreList.get(i).getStudentSeq(), actualScoreList.get(i).getStudentSeq());
                Assertions.assertEquals(expectedScoreList.get(i).getScore(), actualScoreList.get(i).getScore());
            }
        }
    }
    @Test
    void getScoreByStudentSeq() {
        Score[] expectedArray = {
            new Score(1, 30),
            new Score(2, 80),
            new Score(3, 70),
            null
        };
        List<Score> expected = Arrays.asList(expectedArray);
        int[] testSeq = {1, 2, 3, 4};
        Mockito.when(students.findAll()).thenReturn(studentList);
        for(int testCase=0; testCase<studentList.size(); testCase++) {
            Score expectedScore = expected.get(testCase);
            Score actualScore = gradeQueryService.getScoreByStudentSeq(testSeq[testCase]);

            Assertions.assertEquals(Objects.isNull(expectedScore), Objects.isNull(actualScore));

            if(Objects.nonNull(expectedScore) && Objects.nonNull(actualScore)) {
                Assertions.assertEquals(expectedScore.getStudentSeq(), actualScore.getStudentSeq());
                Assertions.assertEquals(expectedScore.getScore(), actualScore.getScore());
            }
        }
    }
}