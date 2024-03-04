package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.ComponentConfig;
import com.nhnacademy.edu.springframework.project.config.ServiceConfig;
import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.service.impl.DefaultStudentService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
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
class StudentServiceTest {
    @InjectMocks
    private DefaultStudentService studentService;

    @Autowired
    private DataLoadService dataLoadService;
    private List<Student> studentList;
    @Mock
    Students students;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        dataLoadService.loadAndMerge();

        Student student1 = new Student(1, "A");
        Student student2 = new Student(2, "B");
        Student student3 = new Student(3, "A");
        Student student4 = new Student(4, "D");
        studentList = List.of(
            student1, student2, student3, student4);

        student1.setScore(new Score(1, 30));
        student2.setScore(new Score(2, 80));
        student3.setScore(new Score(3, 70));
    }

    @Test
    void getPassedStudentsTest() {
        Student passedStudent2 = new Student(2, "B");
        passedStudent2.setScore(new Score(2, 80));
        Student passedStudent3 = new Student(3, "A");
        passedStudent3.setScore(new Score(3, 70));
        List<Student> expectedPassedStudents = List.of(passedStudent2, passedStudent3);

        Mockito.when(students.findAll()).thenReturn(studentList);
        List<Student> actualPassedStudents = (List<Student>) studentService.getPassedStudents();
        Assertions.assertEquals(expectedPassedStudents.size(), actualPassedStudents.size());

        for(int i=0; i<expectedPassedStudents.size(); i++) {
            Student expectedStudent = expectedPassedStudents.get(i);
            Student actualStudent = actualPassedStudents.get(i);

            Assertions.assertEquals(expectedStudent.getName(), actualStudent.getName());
            Assertions.assertEquals(expectedStudent.getSeq(), actualStudent.getSeq());
            Assertions.assertEquals(expectedStudent.getScore().getScore(), actualStudent.getScore().getScore());
        }
    }

    @Test
    void getStudentsOrderByScoreTest() {
        List<Student> expectedOrderedList = studentList.stream().filter(student -> Objects.nonNull(student.getScore())).sorted((o1, o2) -> o2.getScore()
            .getScore()-o1.getScore().getScore()).collect(Collectors.toList());

        Mockito.when(students.findAll()).thenReturn(studentList);
        List<Student> actualOrderedList = (List<Student>) studentService.getStudentsOrderByScore();
        Assertions.assertEquals(expectedOrderedList.size(), actualOrderedList.size());

        for(int i=0; i<expectedOrderedList.size(); i++) {
            Student expectedStudent = expectedOrderedList.get(i);
            Student actualStudent = actualOrderedList.get(i);

            Assertions.assertEquals(expectedStudent.getName(), actualStudent.getName());
            Assertions.assertEquals(expectedStudent.getSeq(), actualStudent.getSeq());
            Assertions.assertEquals(expectedStudent.getScore().getScore(), actualStudent.getScore().getScore());
        }
    }
}