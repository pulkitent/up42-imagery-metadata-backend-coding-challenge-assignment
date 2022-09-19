package com.up42.backendcodingchallenge.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.up42.backendcodingchallenge.model.FeatureCollection;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
