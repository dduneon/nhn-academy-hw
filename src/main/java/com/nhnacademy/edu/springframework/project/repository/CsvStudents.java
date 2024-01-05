package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class CsvStudents implements Students {
    private static final CsvStudents csvStudents = new CsvStudents();
    private static final Collection<Student> studentList = new ArrayList<>();

    /** TODO 3 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Students getInstance() {
        return csvStudents;
    }

    // TODO 7 : student.csv 파일에서 데이터를 읽어 클래스 멤버 변수에 추가하는 로직을 구현하세요.
    // 데이터를 적재하고 읽기 위해서, 적절한 자료구조를 사용하세요.
    @Override
    public void load() {
        studentList.clear();
        try (
            InputStream is = getClass().getResourceAsStream("/data/student.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is))
        ) {
            String inputLine = br.readLine();
            while(inputLine != null && !inputLine.isEmpty()) {
                String[] splitLine = inputLine.split(",");
                if(splitLine.length != 2) {
                    throw new IllegalArgumentException("csv parsing error");
                }
                int studentSeq = Integer.parseInt(splitLine[0]);
                String studentName = splitLine[1];
                Student newStudent = new Student(studentSeq, studentName);
                studentList.add(newStudent);

                inputLine = br.readLine();
            }
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    @Override
    public Collection<Student> findAll() {
        return studentList;
    }

    /**
     * TODO 8 : students 데이터에 score 정보를 추가하세요.
     * @param scores
     */
    @Override
    public void merge(Collection<Score> scores) {
        HashMap<Integer, Score> seqScoreMap = new HashMap<>();
        for(Score score: scores) {
            seqScoreMap.put(score.getStudentSeq(), score);
        }

        for(Student student: studentList) {
            if(seqScoreMap.containsKey(student.getSeq()))
                student.setScore(seqScoreMap.get(student.getSeq()));
        }
    }
}
