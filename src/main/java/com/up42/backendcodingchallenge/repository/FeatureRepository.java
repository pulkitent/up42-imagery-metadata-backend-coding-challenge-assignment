package com.up42.backendcodingchallenge.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.up42.backendcodingchallenge.exception.FileReadException;
import com.up42.backendcodingchallenge.model.Feature;
import com.up42.backendcodingchallenge.model.FeatureCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class FeatureRepository {
  private final String filePath;

  private static final ObjectMapper mapper = new ObjectMapper();
  private static final Logger LOGGER = Logger.getLogger(FeatureRepository.class.getName());

  @Autowired
  public FeatureRepository(@Value("${source.data.file.path}") final String filePath) {
    this.filePath = filePath;
  }

  public List<FeatureCollection> findAll() {
    try {
      LOGGER.log(Level.INFO, "Reading file from path: {0}", filePath);
      final File file = ResourceUtils.getFile(filePath);
      return mapper.readValue(file, new TypeReference<List<FeatureCollection>>() {
      });
    } catch (IOException ex) {
      throw new FileReadException("Failed to deserialize data source JSON file.", ex);
    }
  }

  public Optional<Feature> findById(final UUID id) {
    LOGGER.log(Level.INFO, "Fetching feature by id at repository layer.");

    return this.findAll()
        .stream()
        .flatMap(featureCollection -> featureCollection.getFeatures()
            .stream())
        .filter(feature -> id.equals(feature.getProperty()
            .getId()))
        .findFirst();
  }
}
