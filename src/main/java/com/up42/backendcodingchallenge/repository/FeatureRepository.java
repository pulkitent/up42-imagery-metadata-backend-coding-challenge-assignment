package com.up42.backendcodingchallenge.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.up42.backendcodingchallenge.model.FeatureCollection;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class FeatureRepository {
  private static final ObjectMapper mapper = new ObjectMapper();

  public List<FeatureCollection> findAll() throws IOException {
    final File jsonFile = ResourceUtils.getFile("classpath:static/source-data.json");
    return mapper.readValue(jsonFile, new TypeReference<List<FeatureCollection>>() {});
  }
}
