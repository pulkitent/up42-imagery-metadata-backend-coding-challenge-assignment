package com.up42.backendcodingchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public
class FeatureCollection {
  @JsonProperty("features")
  public
  List<Feature> features;

  @Getter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Feature {
    public UUID id;
    public Long timestamp;
    public Long beginViewingDate;
    public Long endViewingDate;
    public String missionName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Properties properties;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Properties {
      @JsonProperty("id")
      public
      UUID id;
      @JsonProperty("timeStamp")
      public
      Long timeStamp;
      @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
      public
      Acquisition acquisition;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Acquisition {
      @JsonProperty("beginViewingDate")
      public
      Long beginViewingDate;
      @JsonProperty("endViewingDate")
      public
      Long endViewingDate;
      @JsonProperty("missionName")
      public
      String missionName;
    }
  }
}
