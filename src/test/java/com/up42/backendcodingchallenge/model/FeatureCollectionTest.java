package com.up42.backendcodingchallenge.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FeatureCollectionTest {

  @Test
  @DisplayName("Test getFeatureList should return not empty feature list when features are present")
  void testGetOptionalFeatures_ShouldReturnNonEmptyFeatureList() {
    // Arrange
    String encodedString = "cHVsa2l0";
    Property property = new Property(UUID.randomUUID(), 1111L, encodedString, null);
    Feature feature = new Feature(property);
    FeatureCollection featureCollection = new FeatureCollection(Collections.singletonList(feature));

    // Act
    List<Feature> features = featureCollection.getFeatureList();

    // Assert
    assertNotNull(features);
    assertFalse(features.isEmpty());
  }

  @Test
  @DisplayName("Test getFeatureList should return non null but empty feature list when features are not present")
  void testGetOptionalFeatures_ShouldReturnNonNullFeatureList() {
    // Arrange
    FeatureCollection featureCollection = new FeatureCollection(null);

    // Act
    List<Feature> features = featureCollection.getFeatureList();

    // Assert
    assertNotNull(features);
    assertTrue(features.isEmpty());
  }
}
