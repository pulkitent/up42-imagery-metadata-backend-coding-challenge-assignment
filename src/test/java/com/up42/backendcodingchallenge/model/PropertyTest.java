package com.up42.backendcodingchallenge.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertyTest {
  @Test
  @DisplayName("Test getDecodedQuickLook should return Base 64 decoded quicklook when encoded quicklook is present")
  void testGetDecodedQuickLook_ShouldReturnBase64DecodedQuickLook() {
    // Arrange
    String encodedString = "cHVsa2l0";
    Property property = new Property(UUID.randomUUID(), 1111L, encodedString, null);
    String expectedDecodedQuickLook = "pulkit";

    // Act
    byte[] decodedQuickLook = property.getDecodedQuickLook();

    // Assert
    assertArrayEquals(expectedDecodedQuickLook.getBytes(StandardCharsets.UTF_8), decodedQuickLook);
  }

  @Test
  @DisplayName("Test getDecodedQuickLook should return quicklook with 0 size when encoded quicklook is not present")
  void testGetDecodedQuickLook_ShouldReturnZeroSizedQuickLook() {
    // Arrange
    Property property = new Property(UUID.randomUUID(), 1111L, null, null);

    // Act
    byte[] decodedQuickLook = property.getDecodedQuickLook();

    // Assert
    assertEquals(0, decodedQuickLook.length);
  }
}
