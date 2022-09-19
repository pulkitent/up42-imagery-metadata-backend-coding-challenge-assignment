package com.up42.backendcodingchallenge.service;

import com.up42.backendcodingchallenge.dto.FeatureDTO;
import com.up42.backendcodingchallenge.exception.ModelTransformationException;
import com.up42.backendcodingchallenge.model.Feature;
import com.up42.backendcodingchallenge.model.FeatureCollection;
import com.up42.backendcodingchallenge.repository.FeatureRepository;
import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeatureService {
  private final FeatureRepository featureRepository;

  private static final ModelMapper modelMapper = new ModelMapper();

  @Autowired
  public FeatureService(final FeatureRepository featureRepository) {
    this.featureRepository = featureRepository;
  }

  public List<FeatureDTO> getAllFeatures() {
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
    try {
      return modelMapper.map(feature, FeatureDTO.class);
    } catch (IllegalArgumentException | ConfigurationException | MappingException exception) {
      throw new ModelTransformationException("Failed to transform feature model to DTO.", exception);
    }
  }
}
