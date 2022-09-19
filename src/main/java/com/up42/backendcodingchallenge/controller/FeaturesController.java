package com.up42.backendcodingchallenge.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class FeaturesController {

  @GetMapping("/features")
  public List<FeatureCollection.Feature> getFeatures() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    File jsonFile = ResourceUtils.getFile("classpath:static/source-data.json");
    List<FeatureCollection> featureCollections = mapper.readValue(jsonFile, new TypeReference<List<FeatureCollection>>() {
    });

    return featureCollections
        .stream()
        .flatMap(featureCollection -> featureCollection
            .features
            .stream())
        .map(feature -> {
          feature.id = feature.properties.id;
          feature.timestamp = feature.properties.timeStamp;
          feature.beginViewingDate = feature.properties.acquisition.beginViewingDate;
          feature.endViewingDate = feature.properties.acquisition.endViewingDate;
          feature.missionName = feature.properties.acquisition.missionName;
          return feature;
        })
        .collect(Collectors.toList());
  }
}

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class FeatureCollection {
  @JsonProperty("features")
  List<Feature> features;

  @Getter
  @JsonIgnoreProperties(ignoreUnknown = true)
  static class Feature {
    UUID id;
    Long timestamp;
    Long beginViewingDate;
    Long endViewingDate;
    String missionName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Properties properties;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Properties {
      @JsonProperty("id")
      UUID id;
      @JsonProperty("timeStamp")
      Long timeStamp;
      @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
      Acquisition acquisition;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Acquisition {
      @JsonProperty("beginViewingDate")
      Long beginViewingDate;
      @JsonProperty("endViewingDate")
      Long endViewingDate;
      @JsonProperty("missionName")
      String missionName;
    }
  }
}
