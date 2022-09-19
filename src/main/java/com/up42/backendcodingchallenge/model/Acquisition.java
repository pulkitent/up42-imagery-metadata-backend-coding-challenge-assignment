package com.up42.backendcodingchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Acquisition {
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
