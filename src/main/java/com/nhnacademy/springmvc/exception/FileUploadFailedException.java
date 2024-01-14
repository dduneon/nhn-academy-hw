package com.nhnacademy.springmvc.exception;

public class FileUploadFailedException extends RuntimeException {

  public FileUploadFailedException(Exception exception) {
    super(exception);
  }

  public FileUploadFailedException() {
  }
}
