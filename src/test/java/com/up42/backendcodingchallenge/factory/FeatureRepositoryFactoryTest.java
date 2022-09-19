package com.up42.backendcodingchallenge.factory;

import com.up42.backendcodingchallenge.exception.FileReadException;
import com.up42.backendcodingchallenge.repository.FeatureRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FeatureRepositoryFactoryTest {
  @Test
  @DisplayName("Test getFeatureRepository should correctly read JSON file without throwing any exception")
  void testGetFeatureRepository_ShouldNotThrowFileReadException() {
    // Arrange
    String filePath = "classpath:static/source-data.json";
    FeatureRepositoryFactory featureRepositoryFactory = new FeatureRepositoryFactory(filePath);

    // Act & Assert
    FeatureRepository featureRepository = assertDoesNotThrow(featureRepositoryFactory::getFeatureRepository);
    assertNotNull(featureRepository);
  }

  @Test
  @DisplayName("Test getFeatureRepository should throw exception while reading JSON file which is not present")
  void testGetFeatureRepository_ShouldThrowFileReadException() {
    // Arrange
    String filePath = "InCorrectFilePath.json";
    FeatureRepositoryFactory featureRepositoryFactory = new FeatureRepositoryFactory(filePath);

    // Act & Assert
    FileReadException fileReadException = assertThrows(FileReadException.class, featureRepositoryFactory::getFeatureRepository);
    assertEquals("Failed to deserialize data source JSON file.", fileReadException.getLocalizedMessage());
  }
}
