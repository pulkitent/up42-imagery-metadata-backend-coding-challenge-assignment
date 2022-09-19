package com.up42.backendcodingchallenge.factory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.up42.backendcodingchallenge.exception.FileReadException;
import com.up42.backendcodingchallenge.model.FeatureCollection;
import com.up42.backendcodingchallenge.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class FeatureRepositoryFactory {
  private final String filePath;

  private static final ObjectMapper mapper = new ObjectMapper();
  private static final Logger LOGGER = Logger.getLogger(FeatureRepositoryFactory.class.getName());

  @Autowired
  public FeatureRepositoryFactory(@Value("${source.data.file.path}") final String filePath) {
    this.filePath = filePath;
  }

  @Bean
  public FeatureRepository getFeatureRepository() {
    try {
      LOGGER.log(Level.INFO, "Reading file from path: {0}", filePath);
      final File file = ResourceUtils.getFile(filePath);
      return new FeatureRepository(mapper.readValue(file, new TypeReference<List<FeatureCollection>>() {
      }));
    } catch (IOException ex) {
      throw new FileReadException("Failed to deserialize data source JSON file.", ex);
    }
  }
}
