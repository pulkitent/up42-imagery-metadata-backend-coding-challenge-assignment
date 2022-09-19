package com.up42.backendcodingchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Acquisition {
  private final Long beginViewingDate;
  private final Long endViewingDate;
  private final String missionName;

  public Acquisition(@JsonProperty("beginViewingDate") Long beginViewingDate,
                     @JsonProperty("endViewingDate") Long endViewingDate,
                     @JsonProperty("missionName") String missionName) {
    this.beginViewingDate = beginViewingDate;
    this.endViewingDate = endViewingDate;
    this.missionName = missionName;
  }
}
