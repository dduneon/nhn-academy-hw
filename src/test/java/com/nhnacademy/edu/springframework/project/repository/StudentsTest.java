package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import java.util.ArrayList;
import java.util.List;
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
        expected.add(new Student(1, "A"));
        expected.add(new Student(2, "B"));
        expected.add(new Student(3, "C"));
        expected.add(new Student(4, "D"));
    }

    @Test
    void load() {
        students.load();
        Assertions.assertEquals(4, students.findAll().size());
        //TODO throw exception test
    }

    @Test
    void findAll() {
        students.load();
        List<Student> actual = (List<Student>) students.findAll();
        System.out.println(actual.size());

        Assertions.assertEquals(expected.size(), actual.size());

        for(int i=0; i<expected.size(); i++) {
            Assertions.assertEquals(expected.get(i).getSeq(), actual.get(i).getSeq());
            Assertions.assertEquals(expected.get(i).getName(), actual.get(i).getName());
        }
    }

    @Test
    void merge() {
    }
}