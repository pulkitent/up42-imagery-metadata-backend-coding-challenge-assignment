package com.up42.backendcodingchallenge.controller;

import com.up42.backendcodingchallenge.dto.FeatureDTO;
import com.up42.backendcodingchallenge.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class FeatureController {
  private final FeatureService featureService;

  private static final Logger LOGGER = Logger.getLogger(FeatureController.class.getName());

  @Autowired
  public FeatureController(final FeatureService featureService) {
    this.featureService = featureService;
  }

  @GetMapping("/features")
  public ResponseEntity<List<FeatureDTO>> getFeatures() {
    LOGGER.log(Level.INFO, "Request received to fetch all features at controller layer.");

    final List<FeatureDTO> features = featureService.getAllFeatures();
    return new ResponseEntity<>(features, HttpStatus.OK);
  }
}
