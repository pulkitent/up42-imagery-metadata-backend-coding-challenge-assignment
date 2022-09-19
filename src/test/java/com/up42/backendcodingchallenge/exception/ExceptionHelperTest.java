package com.up42.backendcodingchallenge.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExceptionHelperTest {
  @InjectMocks
  private ExceptionHelper exceptionHelper;

  @Test
  @DisplayName("Test handleApplicationException should return HTTP Internal Server Error with 'Something went wrong' as a message when exception was thrown while reading file")
  void testHandleApplicationException_ShouldReturnHttp500WhenFileReadExceptionIsThrown() {
    // Arrange
    FileReadException fileReadException = new FileReadException("Failed to deserialize data source JSON file.", new IOException());

    // Act
    ResponseEntity<Object> responseEntity = exceptionHelper.handleApplicationException(fileReadException);

    // Assert
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    assertEquals("Something went wrong", responseEntity.getBody());
  }

  @Test
  @DisplayName("Test handleApplicationException should return HTTP Internal Server Error with 'Something went wrong' as a message when exception was thrown while deserializing the file")
  void testHandleApplicationException_ShouldReturnHttp500WhenModelTransformationExceptionIsThrown() {
    // Arrange
    ModelTransformationException modelTransformationException = new ModelTransformationException("Failed to transform feature model to DTO.", new IllegalArgumentException());

    // Act
    ResponseEntity<Object> responseEntity = exceptionHelper.handleApplicationException(modelTransformationException);

    // Assert
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    assertEquals("Something went wrong", responseEntity.getBody());
  }
}
