package com.up42.backendcodingchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Acquisition {
  @JsonProperty("beginViewingDate")
  private Long beginViewingDate;
  @JsonProperty("endViewingDate")
  private Long endViewingDate;
  @JsonProperty("missionName")
  private String missionName;
}
