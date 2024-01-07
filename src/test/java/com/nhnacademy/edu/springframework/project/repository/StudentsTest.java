package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentsTest {
    private static Students students;
    private static List<Student> expected;

    @BeforeAll
    static void setup() {
        students = CsvStudents.getInstance();
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

    @Test
    void load() {
        students.load();
        Assertions.assertEquals(4, students.findAll().size());
    }

    @Test
    void findAll() {
        students.load();
        List<Student> actual = (List<Student>) students.findAll();

        Assertions.assertEquals(expected.size(), actual.size());

        for(int i=0; i<expected.size(); i++) {
            Assertions.assertEquals(expected.get(i).getSeq(), actual.get(i).getSeq());
            Assertions.assertEquals(expected.get(i).getName(), actual.get(i).getName());
        }
    }

    @Test
    void merge() {
        List<Score> scores = new ArrayList<>();
        scores.add(new Score(1, 30));
        scores.add(new Score(2, 80));
        scores.add(new Score(3, 70));

        students.load();
        Assertions.assertEquals(4, students.findAll().size());
        students.merge(scores);
        Assertions.assertEquals(4, students.findAll().size());

        List<Student> studentList = (List<Student>) students.findAll();
        for(int i=0; i<expected.size(); i++) {
            Score expectedScore = expected.get(i).getScore();
            Score actualScore = studentList.get(i).getScore();
            Assertions.assertEquals(Objects.isNull(expectedScore), Objects.isNull(actualScore));
            if(Objects.nonNull(expectedScore) && Objects.nonNull(actualScore)) {
                Assertions.assertEquals(expected.get(i).getSeq(), studentList.get(i).getSeq());
                Assertions.assertEquals(expected.get(i).getName(), studentList.get(i).getName());
                Assertions.assertEquals(expectedScore.getScore(), actualScore.getScore());
            }
        }
    }
}