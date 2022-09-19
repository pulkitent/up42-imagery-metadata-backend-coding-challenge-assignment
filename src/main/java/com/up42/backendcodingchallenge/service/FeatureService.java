package com.up42.backendcodingchallenge.service;

import com.up42.backendcodingchallenge.dto.FeatureDTO;
import com.up42.backendcodingchallenge.model.Feature;
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
            .getFeatures()
            .stream())
        .map(this::getDTOFromModel)
        .collect(Collectors.toList());
  }

  private FeatureDTO getDTOFromModel(final Feature feature) {
    final FeatureDTO featureDTO = new FeatureDTO();

    featureDTO.setPropertyId((feature.getProperties().getId()));
    featureDTO.setPropertyTimestamp(feature.getProperties().getTimeStamp());
    featureDTO.setPropertyAcquisitionBeginViewingDate(feature.getProperties().getAcquisition().getBeginViewingDate());
    featureDTO.setPropertyAcquisitionEndViewingDate(feature.getProperties().getAcquisition().getEndViewingDate());
    featureDTO.setPropertyAcquisitionMissionName(feature.getProperties().getAcquisition().getMissionName());

    return featureDTO;
  }
}
