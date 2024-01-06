package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentServiceTest {
    static StudentService service;
    static List<Student> expected;
    Students students;
    Scores scores;

    @BeforeAll
    static void setup() {
        service = new DefaultStudentService();
        expected = new ArrayList<>();
        Student student1 = new Student(1, "A");
        student1.setScore(new Score(1, 30));

        Student student2 = new Student(2, "B");
        student2.setScore(new Score(2, 80));

        Student student3 = new Student(3, "A");
        student3.setScore(new Score(3, 70));

        Student student4 = new Student(4, "D");

        expected.add(student1);
        expected.add(student2);
        expected.add(student3);
        expected.add(student4);
    }

    @BeforeEach
    void loadData() {
        students = CsvStudents.getInstance();
        scores = CsvScores.getInstance();

        new CsvDataLoadService().loadAndMerge();
    }
    @Test
    void getPassedStudentsTest() {
        List<Student> expectedPassedStudents = new ArrayList<>();
        Student passedStudent2 = new Student(2, "B");
        passedStudent2.setScore(new Score(2, 80));
        Student passedStudent3 = new Student(3, "A");
        passedStudent3.setScore(new Score(3, 70));
        expectedPassedStudents.add(passedStudent2);
        expectedPassedStudents.add(passedStudent3);

        List<Student> actualPassedStudents = (List<Student>) service.getPassedStudents();
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
        List<Student> expectedOrderedList = expected.stream().filter(student -> Objects.nonNull(student.getScore())).sorted((o1, o2) -> o2.getScore()
            .getScore()-o1.getScore().getScore()).collect(Collectors.toList());
        List<Student> actualOrderedList = (List<Student>) service.getStudentsOrderByScore();
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