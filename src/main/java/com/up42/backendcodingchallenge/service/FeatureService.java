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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class FeatureService {
  private final FeatureRepository featureRepository;

  private static final ModelMapper modelMapper = new ModelMapper();
  private static final Logger LOGGER = Logger.getLogger(FeatureService.class.getName());

  @Autowired
  public FeatureService(final FeatureRepository featureRepository) {
    this.featureRepository = featureRepository;
  }

  @Cacheable("features")
  public List<FeatureDTO> getAllFeatures() {
    LOGGER.log(Level.INFO, "Fetching features at service layer.");

    final List<FeatureCollection> featureCollections = featureRepository.findAll();
    return featureCollections
        .stream()
        .flatMap(featureCollection -> featureCollection
            .getFeatures()
            .stream())
        .map(this::getDTOFromModel)
        .collect(Collectors.toList());
  }
  
  public byte[] getQuickLookByFeatureId(final UUID id) {
    return featureRepository
        .findById(id)
        .map(feature -> Base64.getDecoder().decode(feature.getProperty().getQuickLook()))
        .orElseGet(() -> new byte[0]);
  }

  private FeatureDTO getDTOFromModel(final Feature feature) {
    try {
      return modelMapper.map(feature, FeatureDTO.class);
    } catch (IllegalArgumentException | ConfigurationException | MappingException exception) {
      throw new ModelTransformationException("Failed to transform feature model to DTO.", exception);
    }
  }
}
