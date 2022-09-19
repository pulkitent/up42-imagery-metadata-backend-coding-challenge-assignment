package com.up42.backendcodingchallenge.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
public class ExceptionHelper {
  private static final Logger LOGGER = Logger.getLogger(ExceptionHelper.class.getName());

  @ExceptionHandler(value = {FileReadException.class, ModelTransformationException.class})
  public ResponseEntity<Object> handleApplicationException(RuntimeException runtimeException) {
    LOGGER.severe(runtimeException.getLocalizedMessage());
    return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
