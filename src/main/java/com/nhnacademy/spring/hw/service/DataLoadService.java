package com.nhnacademy.spring.hw.service;

public interface DataLoadService {
  void loadDataFromFile(String filePath);
  String loadFilePathFromProperties();
}
