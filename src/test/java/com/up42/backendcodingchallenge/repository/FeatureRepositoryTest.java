package com.up42.backendcodingchallenge.repository;

import com.up42.backendcodingchallenge.model.Feature;
import com.up42.backendcodingchallenge.model.FeatureCollection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class FeatureRepositoryTest {
  @Test
  @DisplayName("Test findAll should return non empty feature collection list when features are present")
  void testFindAll_ShouldReturnNonEmptyFeatureCollectionList() {
    // Arrange
    Feature feature = new Feature(null);
    FeatureRepository featuresRepository = new FeatureRepository("classpath:static/source-data.json");

    // Act
    List<FeatureCollection> featureCollections = featuresRepository.findAll();

    // Assert
    assertFalse(featureCollections.isEmpty());
  }
}
