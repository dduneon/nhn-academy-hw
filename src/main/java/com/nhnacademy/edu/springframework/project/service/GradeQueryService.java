package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.Score;

import java.util.List;

public interface GradeQueryService {
    List<Score> getScoreByStudentName(String name);
    Score getScoreByStudentSeq(int seq);
}
