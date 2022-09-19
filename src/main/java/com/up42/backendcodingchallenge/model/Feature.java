package com.up42.backendcodingchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature {
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Properties properties;
}
