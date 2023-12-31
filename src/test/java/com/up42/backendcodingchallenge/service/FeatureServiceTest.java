package com.up42.backendcodingchallenge.service;

import com.up42.backendcodingchallenge.dto.FeatureDTO;
import com.up42.backendcodingchallenge.model.Acquisition;
import com.up42.backendcodingchallenge.model.Feature;
import com.up42.backendcodingchallenge.model.FeatureCollection;
import com.up42.backendcodingchallenge.model.Property;
import com.up42.backendcodingchallenge.repository.FeatureRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class FeatureServiceTest {
  @InjectMocks
  private FeatureService featuresService;
  @Mock
  private FeatureRepository featuresRepository;

  @Test
  @DisplayName("Test GetAllFeatures should return non empty feature list when features are present")
  void testGetAllFeatures_ShouldReturnAllFeatures() {
    // Arrange
    String encodedString = "cHVsa2l0";
    Acquisition acquisition = new Acquisition(111L, 222L, "mission-name");
    UUID uuid = UUID.randomUUID();
    Property property = new Property(uuid, 1111L, encodedString, acquisition);
    Feature feature = new Feature(property);
    Mockito.when(featuresRepository.findAll()).thenReturn(singletonList(new FeatureCollection(singletonList(feature))));

    //Act
    List<FeatureDTO> featureDTOS = featuresService.getAllFeatures();

    //Assert
    assertFalse(featureDTOS.isEmpty());
    assertEquals(uuid, featureDTOS.get(0).getPropertyId());
    assertEquals(1111L, featureDTOS.get(0).getPropertyTimestamp());
    assertEquals(111L, featureDTOS.get(0).getPropertyAcquisitionBeginViewingDate());
    assertEquals(222L, featureDTOS.get(0).getPropertyAcquisitionEndViewingDate());
    assertEquals("mission-name", featureDTOS.get(0).getPropertyAcquisitionMissionName());
  }

  @Test
  @DisplayName("Test GetAllFeatures should return empty feature list when features are not present")
  void testGetAllFeatures_ShouldReturnEmptyFeatureListWhenFeaturesAreNotPresent() {
    // Arrange
    Mockito.when(featuresRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

    //Act
    List<FeatureDTO> featureDTOS = featuresService.getAllFeatures();

    //Assert
    Assertions.assertTrue(featureDTOS.isEmpty());
  }

  @Test
  @DisplayName("Test GetQuickLookByFeatureId should return decoded quicklook when quicklook is present for given featureId")
  void testGetQuickLookByFeatureId_ShouldReturnDecodedQuickLookWhenQuickLookIsPresent() {
    // Arrange
    String encodedString = "cHVsa2l0";
    Acquisition acquisition = new Acquisition(111L, 222L, "mission-name");
    UUID uuid = UUID.randomUUID();
    Property property = new Property(uuid, 1111L, encodedString, acquisition);
    Feature feature = new Feature(property);
    Mockito.when(featuresRepository.findById(uuid)).thenReturn(Optional.of(feature));
    byte[] expectedDecodedQuickLook = Base64.getDecoder().decode(encodedString.getBytes(StandardCharsets.UTF_8));

    // Act
    byte[] quickLookByFeatureId = featuresService.getQuickLookByFeatureId(uuid);

    // Assert
    assertTrue(quickLookByFeatureId.length > 0);
    assertArrayEquals(expectedDecodedQuickLook, quickLookByFeatureId);
  }

  @Test
  @DisplayName("Test GetQuickLookByFeatureId should return quicklook with 0 size when feature is not present for given featureId")
  void testGetQuickLookByFeatureId_ShouldReturnEmptyQuickLookFeatureIdIsNotPresent() {
    // Arrange
    UUID uuid = UUID.randomUUID();
    Mockito.when(featuresRepository.findById(uuid)).thenReturn(Optional.empty());

    // Act
    byte[] quickLookByFeatureId = featuresService.getQuickLookByFeatureId(uuid);

    // Assert
    assertEquals(0, quickLookByFeatureId.length);
  }
}
