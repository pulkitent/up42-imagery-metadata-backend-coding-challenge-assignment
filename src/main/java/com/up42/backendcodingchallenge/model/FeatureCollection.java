package com.up42.backendcodingchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public final class FeatureCollection {
  private final List<Feature> features;

  public FeatureCollection(@JsonProperty("features") List<Feature> features) {
    this.features = features;
  }

  /*
   * Get list of feature domain entity
   * @return A a list of feature if present, or
   *         an empty list.
   * */
  public List<Feature> getFeatureList() {
    return Optional.ofNullable(this.getFeatures()).orElseGet(Collections::emptyList);
  }
}
