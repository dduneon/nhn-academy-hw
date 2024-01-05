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
    }

    @Test
    void merge() {
    }
}