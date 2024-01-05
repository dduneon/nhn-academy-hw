package com.nhnacademy.edu.springframework.project.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvScores implements Scores {

    private static final CsvScores csvScores = new CsvScores();
    private static List<Score> scoreList = new ArrayList<>();

    private CsvScores(){}

    /** TODO 2 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Scores getInstance() {
        return csvScores;
    }

    // TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        scoreList.clear();
        try (
            InputStream inputStream = getClass().getResourceAsStream("/data/score.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            String inputLine = br.readLine();
            while(inputLine != null && !inputLine.isEmpty()) {
                String[] splitLine = inputLine.split(",");
                if(splitLine.length != 2) {
                    throw new IllegalArgumentException("csv parsing error");
                }
                int studentSeq = Integer.parseInt(splitLine[0]);
                int studentScore = Integer.parseInt(splitLine[1]);
                Score newScore = new Score(studentSeq, studentScore);
                scoreList.add(newScore);
                inputLine = br.readLine();
            }
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    @Override
    public List<Score> findAll() {
        return scoreList;
    }
}
