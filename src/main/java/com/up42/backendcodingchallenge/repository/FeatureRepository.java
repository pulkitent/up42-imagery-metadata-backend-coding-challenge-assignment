package com.up42.backendcodingchallenge.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.up42.backendcodingchallenge.exception.FileReadException;
import com.up42.backendcodingchallenge.model.FeatureCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class FeatureRepository {
  private final String filePath;

  private static final ObjectMapper mapper = new ObjectMapper();

  @Autowired
  public FeatureRepository(@Value("${source.data.file.path}") final String filePath) {
    this.filePath = filePath;
  }

  public List<FeatureCollection> findAll() {
    try {
      final File file = ResourceUtils.getFile(filePath);
      return mapper.readValue(file, new TypeReference<List<FeatureCollection>>() {
      });
    } catch (IOException ex) {
      throw new FileReadException("Failed to deserialize data source JSON file.", ex);
    }
  }
}
