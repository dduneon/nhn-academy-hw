package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.config.ComponentConfig;
import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvScores;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ComponentConfig.class)
class ScoresTest {
    private Scores scores;
    private List<Score> expected;

    @BeforeEach
    void setup() {
        expected = List.of(
            new Score(1, 30),
            new Score(2, 80),
            new Score(3, 70)
        );
    }

    @Test
    void load() {
        scores.load();
        Assertions.assertEquals(3, scores.findAll().size());
        List<Score> actual = scores.findAll();
        Assertions.assertEquals(expected.size(), actual.size());
        for(int i=0; i<expected.size(); i++) {
            Assertions.assertEquals(expected.get(i).getStudentSeq(), actual.get(i).getStudentSeq());
            Assertions.assertEquals(expected.get(i).getScore(), actual.get(i).getScore());
        }
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