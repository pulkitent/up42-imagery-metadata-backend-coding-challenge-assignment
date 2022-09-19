package com.up42.backendcodingchallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FeatureDTO {
  @JsonProperty("id")
  private UUID propertyId;
  @JsonProperty("timestamp")
  private Long propertyTimestamp;
  @JsonProperty("beginViewingDate")
  private Long propertyAcquisitionBeginViewingDate;
  @JsonProperty("endViewingDate")
  private Long propertyAcquisitionEndViewingDate;
  @JsonProperty("missionName")
  private String propertyAcquisitionMissionName;
}
