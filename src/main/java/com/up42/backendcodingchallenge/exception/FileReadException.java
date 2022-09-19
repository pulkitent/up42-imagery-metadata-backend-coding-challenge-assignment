package com.up42.backendcodingchallenge.exception;

public class FileReadException extends RuntimeException {
  public FileReadException(String message, Throwable cause) {
    super(message, cause);
  }
}
