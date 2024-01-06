package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.ComponentConfig;
import com.nhnacademy.edu.springframework.project.config.ServiceConfig;
import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvStudents;
import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.service.impl.CsvDataLoadService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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

class DataLoadServiceTest {

    @Mock
    Scores scores;
    @Mock
    Students students;
    @InjectMocks
    private CsvDataLoadService dataLoadService;
    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadAndMerge() {
        List<Score> mockScoreList = Arrays.asList(
            new Score(1, 30),
            new Score(2, 80),
            new Score(3, 70)
        );

        Mockito.when(scores.findAll()).thenReturn(mockScoreList);
        dataLoadService.loadAndMerge();
        Mockito.verify(scores, Mockito.times(1)).load();
        Mockito.verify(students, Mockito.times(1)).load();
        Mockito.verify(students, Mockito.atLeastOnce()).merge(mockScoreList);
    }
}