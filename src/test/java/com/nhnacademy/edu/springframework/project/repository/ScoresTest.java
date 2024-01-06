package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.Score;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ScoresTest {
    private static Scores scores;
    private static List<Score> expected;

    @BeforeAll
    static void setup() {
        scores = CsvScores.getInstance();
        expected = new ArrayList<>();
        expected.add(new Score(1, 30));
        expected.add(new Score(2, 80));
        expected.add(new Score(3, 70));
    }

    @Test
    void load() {
        scores.load();
        Assertions.assertEquals(3, scores.findAll().size());
    }

    @Test
    void findAll() {
        scores.load();
        List<Score> actual = scores.findAll();
        System.out.println(actual.size());
        Assertions.assertEquals(expected.size(), actual.size());

        for(int i=0; i<expected.size(); i++) {
            Assertions.assertEquals(expected.get(i).getStudentSeq(), actual.get(i).getStudentSeq());
            Assertions.assertEquals(expected.get(i).getScore(), actual.get(i).getScore());
        }
    }
}