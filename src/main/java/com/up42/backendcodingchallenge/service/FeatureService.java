package com.up42.backendcodingchallenge.service;

import com.up42.backendcodingchallenge.dto.FeatureDTO;
import com.up42.backendcodingchallenge.model.FeatureCollection;
import com.up42.backendcodingchallenge.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeatureService {
  private final FeatureRepository featureRepository;

  @Autowired
  public FeatureService(final FeatureRepository featureRepository) {
    this.featureRepository = featureRepository;
  }

  public List<FeatureDTO> getFeatures() throws IOException {
    final List<FeatureCollection> featureCollections = featureRepository.findAll();

    return featureCollections
        .stream()
        .flatMap(featureCollection -> featureCollection
            .features
            .stream())
        .map(feature -> {
          FeatureDTO featureDTO = new FeatureDTO();
          featureDTO.setPropertyId((feature.properties.id));
          featureDTO.setPropertyTimestamp(feature.properties.timeStamp);
          featureDTO.setPropertyAcquisitionBeginViewingDate(feature.properties.acquisition.beginViewingDate);
          featureDTO.setPropertyAcquisitionEndViewingDate(feature.properties.acquisition.endViewingDate);
          featureDTO.setPropertyAcquisitionMissionName(feature.properties.acquisition.missionName);
          return featureDTO;
        })
        .collect(Collectors.toList());
  }
}
