package com.up42.backendcodingchallenge.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHelper {
  @ExceptionHandler(value = {FileReadException.class, ModelTransformationException.class})
  public ResponseEntity<Object> handleApplicationException(RuntimeException runtimeException) {
    return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
