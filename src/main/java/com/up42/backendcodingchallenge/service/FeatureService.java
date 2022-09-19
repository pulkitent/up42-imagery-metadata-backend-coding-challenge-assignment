package com.up42.backendcodingchallenge.service;


import com.up42.backendcodingchallenge.dto.FeatureDTO;
import com.up42.backendcodingchallenge.exception.ModelTransformationException;
import com.up42.backendcodingchallenge.model.Feature;
import com.up42.backendcodingchallenge.repository.FeatureRepository;
import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/*
 * Job of this class is to encapsulate the feature domain entity and perform business logics
 * */
@Service
public class FeatureService {
  private final FeatureRepository featureRepository;

  private static final ModelMapper modelMapper = new ModelMapper();
  private static final Logger LOGGER = Logger.getLogger(FeatureService.class.getName());

  @Autowired
  public FeatureService(final FeatureRepository featureRepository) {
    this.featureRepository = featureRepository;
  }

  /*
   * Get all available features.
   * @exception ModelTransformationException if fails to transform model to DTO
   * @return A list of feature data transfer object, or  or
   *         an empty list if features are nit present.
   * */
  @Cacheable("features")
  public List<FeatureDTO> getAllFeatures() {
    LOGGER.log(Level.INFO, "Fetching features at service layer.");

    return featureRepository.findAll()
        .stream()
        .filter(Objects::nonNull)
        .flatMap(featureCollection -> featureCollection
            .getFeatureList()
            .stream())
        .map(this::getDTOFromModel)
        .collect(Collectors.toList());
  }

  /*
   * Get quicklook by given id.
   * @return A decoded byte array of quicklook if feature if found for given id, or
   *         an empty byte array if feature is not found for given id.
   * */
  @Cacheable("quicklook")
  public byte[] getQuickLookByFeatureId(final UUID id) {
    LOGGER.log(Level.INFO, "Fetching quicklook at service layer.");

    return featureRepository
        .findById(id)
        .map(Feature::getDecodedQuickLook)
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
