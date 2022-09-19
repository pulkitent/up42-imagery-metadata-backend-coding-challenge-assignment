package com.up42.backendcodingchallenge.repository;

import com.up42.backendcodingchallenge.model.Feature;
import com.up42.backendcodingchallenge.model.FeatureCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * Job of this class is to represent repository of features
 * */
public class FeatureRepository {
  private final List<FeatureCollection> featureCollections;

  private static final Logger LOGGER = Logger.getLogger(FeatureRepository.class.getName());

  @Autowired
  public FeatureRepository(final List<FeatureCollection> featureCollections) {
    this.featureCollections = featureCollections;
  }

  /*
   * Find all available features.
   * @return A list of feature collection domain entity
   * */
  public List<FeatureCollection> findAll() {
    return this.featureCollections;
  }

  /*
   * Find a feature by it's id.
   * @param id - feature id to get a feature
   * @return A feature domain entity wrapped inside optional
   * */
  public Optional<Feature> findById(final UUID id) {
    LOGGER.log(Level.INFO, "Fetching feature by id at repository layer.");

    return this.featureCollections
        .stream()
        .flatMap(featureCollection -> featureCollection.getFeatures()
            .stream())
        .filter(feature -> id.equals(feature.getProperty()
            .getId()))
        .findFirst();
  }
}
