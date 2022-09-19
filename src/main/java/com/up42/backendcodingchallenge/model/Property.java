package com.up42.backendcodingchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Property {
  @JsonProperty("id")
  private UUID id;
  @JsonProperty("timeStamp")
  private Long timeStamp;
  @JsonProperty("acquisition")
  private Acquisition acquisition;
}
