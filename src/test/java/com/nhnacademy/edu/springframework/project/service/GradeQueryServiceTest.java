package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.domain.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GradeQueryServiceTest {
    static GradeQueryService gradeQueryService;
    static List<Student> expected;

    @BeforeAll
    static void setup() {
        gradeQueryService = new DefaultGradeQueryService();
        new CsvDataLoadService().loadAndMerge();

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
    void getScoreByStudentName() {
        String[] testNames = {"A", "B", "C", "D"};
        for(String name: testNames) {
            List<Score> actualScores = gradeQueryService.getScoreByStudentName(name);
            List<Score> expectedScores = expected.stream().filter(student -> student.getName().equals(name)).map(Student::getScore).collect(
                Collectors.toList());

            Assertions.assertEquals(Objects.isNull(expectedScores), Objects.isNull(actualScores));
            if(Objects.nonNull(expectedScores) && Objects.nonNull(actualScores)) {
                Assertions.assertEquals(expectedScores.size(), actualScores.size());
                for (int i = 0; i < expectedScores.size(); i++) {
                    if(Objects.nonNull(expectedScores.get(i)) && Objects.nonNull(actualScores.get(i))) {
                        Assertions.assertEquals(expectedScores.get(i).getStudentSeq(),
                            actualScores.get(i).getStudentSeq());
                        Assertions.assertEquals(expectedScores.get(i).getScore(),
                            actualScores.get(i).getScore());
                    }
                }
            }
        }
    }

    @Test
    void getScoreByStudentSeq() {
        int[] testSeq = {1, 2, 3, 4};
        for(int seq: testSeq) {
            Score actualScore = gradeQueryService.getScoreByStudentSeq(seq);
            Score expectedScore;
            try {
                expectedScore = expected.stream().filter(student -> student.getSeq() == seq).map(Student::getScore)
                    .findFirst().orElse(null);
            } catch (NullPointerException npe) {
                expectedScore = null;
            }

            Assertions.assertEquals(Objects.isNull(expectedScore), Objects.isNull(actualScore));
            if(Objects.nonNull(expectedScore) && Objects.nonNull(actualScore)) {
                Assertions.assertEquals(expectedScore.getStudentSeq(), actualScore.getStudentSeq());
                Assertions.assertEquals(expectedScore.getScore(), actualScore.getScore());
            }
        }
    }
}