package com.up42.backendcodingchallenge.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class FeatureTest {
  @Test
  @DisplayName("Test getDecodedQuickLook should return Base 64 decoded quicklook when encoded quicklook is present")
  void testGetDecodedQuickLook_ShouldReturnBase64DecodedQuickLook() {
    // Arrange
    String encodedString = "cHVsa2l0";
    Property property = new Property(UUID.randomUUID(), 1111L, encodedString, null);
    Feature feature = new Feature(property);
    String expectedDecodedQuickLook = "pulkit";

    // Act
    byte[] decodedQuickLook = feature.getDecodedQuickLook();

    // Assert
    assertArrayEquals(expectedDecodedQuickLook.getBytes(StandardCharsets.UTF_8), decodedQuickLook);
  }

  @Test
  @DisplayName("Test getDecodedQuickLook should return quicklook with 0 size when encoded quicklook is not present")
  void testGetDecodedQuickLook_ShouldReturnZeroSizedQuickLook() {
    // Arrange
    Feature feature = new Feature(null);

    // Act
    byte[] decodedQuickLook = feature.getDecodedQuickLook();

    // Assert
    assertEquals(0, decodedQuickLook.length);
  }
}
