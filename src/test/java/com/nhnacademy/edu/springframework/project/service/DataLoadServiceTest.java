package com.nhnacademy.edu.springframework.project.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataLoadServiceTest {
    DataLoadService dataLoadService;

    @BeforeEach
    void setup() {
        dataLoadService = new CsvDataLoadService();

    }

    @Test
    void loadAndMerge() {

    }
}