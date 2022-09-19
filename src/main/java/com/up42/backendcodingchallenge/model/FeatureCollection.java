package com.up42.backendcodingchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public final class FeatureCollection {
  private final List<Feature> features;

  public FeatureCollection(@JsonProperty("features") List<Feature> features) {
    this.features = features;
  }
}
