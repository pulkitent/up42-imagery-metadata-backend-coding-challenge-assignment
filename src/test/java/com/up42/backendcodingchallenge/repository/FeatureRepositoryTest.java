package com.up42.backendcodingchallenge.repository;

import com.up42.backendcodingchallenge.model.Acquisition;
import com.up42.backendcodingchallenge.model.Feature;
import com.up42.backendcodingchallenge.model.FeatureCollection;
import com.up42.backendcodingchallenge.model.Property;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FeatureRepositoryTest {
  @Test
  @DisplayName("Test findAll should return non empty feature collection list when features are present")
  void testFindAll_ShouldReturnNonEmptyFeatureCollectionList() {
    // Arrange
    Feature feature = new Feature(null);
    FeatureRepository featuresRepository = new FeatureRepository(singletonList(new FeatureCollection(singletonList(feature))));

    // Act
    List<FeatureCollection> featureCollections = featuresRepository.findAll();

    // Assert
    assertFalse(featureCollections.isEmpty());
  }

  @Test
  @DisplayName("Test findById should return feature when feature is present for given featureId")
  void testFindById_ShouldReturnFeature() {
    // Arrange
    UUID uuid = UUID.randomUUID();
    Acquisition acquisition = new Acquisition(111L, 222L, "mission-name");
    Property property = new Property(uuid, 1111L, "Base64-encoded-string", acquisition);
    Feature feature = new Feature(property);
    FeatureRepository featuresRepository = new FeatureRepository(singletonList(new FeatureCollection(singletonList(feature))));

    // Act
    Optional<Feature> optionalFeature = featuresRepository.findById(uuid);

    // Assert
    assertTrue(optionalFeature.isPresent());
    assertNotNull(optionalFeature.get().getProperty());
    assertEquals(uuid, optionalFeature.get().getProperty().getId());
    assertEquals("Base64-encoded-string", optionalFeature.get().getProperty().getQuickLook());
    assertEquals(1111L, optionalFeature.get().getProperty().getTimeStamp());
    assertNotNull(optionalFeature.get().getProperty().getAcquisition());
    assertEquals(111L, optionalFeature.get().getProperty().getAcquisition().getBeginViewingDate());
    assertEquals(222L, optionalFeature.get().getProperty().getAcquisition().getEndViewingDate());
    assertEquals("mission-name", optionalFeature.get().getProperty().getAcquisition().getMissionName());
  }
}
