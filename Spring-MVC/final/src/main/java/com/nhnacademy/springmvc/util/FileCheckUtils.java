package com.nhnacademy.springmvc.util;

public class FileCheckUtils {

  private FileCheckUtils() {
  }

  public static boolean CheckFilenameExtension(String fileName) {
    return fileName.contains(".gif") || fileName.contains(".jpg") || fileName.contains(".jpeg") || fileName.contains(".png");
  }
}
