package com.up42.backendcodingchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Objects;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Feature {
  private final Property property;

  public Feature(@JsonProperty("properties") Property property) {
    this.property = property;
  }

  /*
   * Get Base64 decoded quicklook.
   * @return A Base64 decoded byte array of quicklook
   * */
  public byte[] getDecodedQuickLook() {
    if (Objects.isNull(this.getProperty())) {
      return new byte[0];
    }
    return this.getProperty().getDecodedQuickLook();
  }
}
