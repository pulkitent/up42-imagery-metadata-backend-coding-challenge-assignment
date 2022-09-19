package com.up42.backendcodingchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Property {
  private final UUID id;
  private final Long timeStamp;
  private final String quickLook;
  private final Acquisition acquisition;

  public Property(@JsonProperty("uid") UUID id,
                  @JsonProperty("timestamp") Long timeStamp,
                  @JsonProperty("quicklook") String quickLook,
                  @JsonProperty("acquisition") Acquisition acquisition) {
    this.id = id;
    this.timeStamp = timeStamp;
    this.quickLook = quickLook;
    this.acquisition = acquisition;
  }

  byte[] getDecodedQuickLook() {
    if (Objects.isNull(this.getQuickLook())
        || this.getQuickLook().isEmpty()) {
      return new byte[0];
    }
    return Base64.getDecoder().decode(this.getQuickLook());
  }
}
