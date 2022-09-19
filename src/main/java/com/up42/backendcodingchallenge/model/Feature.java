package com.up42.backendcodingchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature {
  public UUID id;
  public Long timestamp;
  public Long beginViewingDate;
  public Long endViewingDate;
  public String missionName;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  public Properties properties;
}
