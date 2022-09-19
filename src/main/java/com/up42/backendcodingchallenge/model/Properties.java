package com.up42.backendcodingchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {
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
