package com.up42.backendcodingchallenge.controller;

import com.up42.backendcodingchallenge.dto.FeatureDTO;
import com.up42.backendcodingchallenge.service.FeatureService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FeatureControllerTest {
  @InjectMocks
  private FeatureController featureController;
  @Mock
  private FeatureService featureService;

  @Test
  @DisplayName("Test getFeatures should return HTTP 200 with feature list having one feature")
  void testGetFeatures_ShouldReturnHttpOkWithOneFeature() {
    // Arrange
    FeatureDTO featureDTO = new FeatureDTO();
    featureDTO.setPropertyId(UUID.randomUUID());
    featureDTO.setPropertyTimestamp(12345678L);
    featureDTO.setPropertyAcquisitionMissionName("MissionName");
    featureDTO.setPropertyAcquisitionEndViewingDate(234567L);
    featureDTO.setPropertyAcquisitionBeginViewingDate(4576790L);
    List<FeatureDTO> featureDTOS = Collections.singletonList(featureDTO);
    Mockito.when(featureService.getAllFeatures()).thenReturn(featureDTOS);

    // Act
    ResponseEntity<List<FeatureDTO>> featuresResponse = featureController.getFeatures();

    // Assert
    assertEquals(HttpStatus.OK, featuresResponse.getStatusCode());
    assertEquals(featureDTOS, featuresResponse.getBody());
  }

  @Test
  @DisplayName("Test getFeatures should return HTTP 200 with empty feature list when features are not present")
  void testGetFeatures_ShouldReturnHttpOkWithEmptyList() {
    // Arrange
    Mockito.when(featureService.getAllFeatures()).thenReturn(Collections.EMPTY_LIST);

    // Act
    ResponseEntity<List<FeatureDTO>> featuresResponse = featureController.getFeatures();

    // Assert
    assertEquals(HttpStatus.OK, featuresResponse.getStatusCode());
    assertNotNull(featuresResponse.getBody());
    assertEquals(0, featuresResponse.getBody().size());
  }

  @Test
  @DisplayName("Test quickLook should return HTTP 200 with decoded quicklook when quicklook is present for given featureId")
  void testGetQuicklook_ShouldReturnHttpOkWithDecodedQuickLookWhenQuickLookIsPresent() {
    // Arrange
    UUID uuid = UUID.randomUUID();
    String encodedString = "cHVsa2l0";
    byte[] expectedDecodedQuickLook = Base64.getDecoder().decode(encodedString.getBytes(StandardCharsets.UTF_8));
    Mockito.when(featureService.getQuickLookByFeatureId(uuid)).thenReturn(expectedDecodedQuickLook);

    // Act
    ResponseEntity<byte[]> decodedQuickLookResponse = featureController.getQuicklook(uuid);

    // Assert
    assertEquals(HttpStatus.OK, decodedQuickLookResponse.getStatusCode());
    assertNotNull(decodedQuickLookResponse.getHeaders());
    assertEquals(MediaType.IMAGE_PNG, decodedQuickLookResponse.getHeaders().getContentType());
    assertEquals(expectedDecodedQuickLook, decodedQuickLookResponse.getBody());
  }

  @Test
  @DisplayName("Test quickLook should return HTTP 404 with no response body when featureId is not found")
  void testGetQuicklook_ShouldReturnHttpNotFoundWithEmptyResponseBodyWhenFeatureIdIsNotPresent() {
    // Arrange
    UUID uuid = UUID.randomUUID();
    Mockito.when(featureService.getQuickLookByFeatureId(uuid)).thenReturn(new byte[0]);

    // Act
    ResponseEntity<byte[]> decodedQuickLookResponse = featureController.getQuicklook(uuid);

    // Assert
    assertEquals(HttpStatus.NOT_FOUND, decodedQuickLookResponse.getStatusCode());
    assertNotNull(decodedQuickLookResponse.getHeaders());
    assertEquals(MediaType.IMAGE_PNG, decodedQuickLookResponse.getHeaders().getContentType());
    assertNull(decodedQuickLookResponse.getBody());
  }
}
