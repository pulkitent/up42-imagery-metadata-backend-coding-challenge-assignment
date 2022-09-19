package com.up42.backendcodingchallenge.controller;

import com.up42.backendcodingchallenge.dto.FeatureDTO;
import com.up42.backendcodingchallenge.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Job of this class is to represent public REST API to processing incoming requests
 * */
@RestController
public class FeatureController {
  private final FeatureService featureService;

  private static final Logger LOGGER = Logger.getLogger(FeatureController.class.getName());

  @Autowired
  public FeatureController(final FeatureService featureService) {
    this.featureService = featureService;
  }

  /*
   * Get all available features.
   * @return A list of feature data transfer object wrapped inside ResponseEntity if features are present, or
   *         an empty list if features are not present.
   * */
  @GetMapping("/features")
  public ResponseEntity<List<FeatureDTO>> getFeatures() {
    LOGGER.log(Level.INFO, "Request received to fetch all features at controller layer.");

    final List<FeatureDTO> features = featureService.getAllFeatures();
    return new ResponseEntity<>(features, HttpStatus.OK);
  }

  /*
   * Get quicklook by given featureId.
   * @return A decoded byte array of quicklook wrapped inside ResponseEntity if feature is found for given featureId, or
   *         empty ResponseEntity if feature is not found for given featureId.
   * */
  @GetMapping(value = "/features/{featureId}/quicklook")
  public ResponseEntity<byte[]> getQuicklook(@PathVariable("featureId") UUID featureId) {
    LOGGER.log(Level.INFO, "Request received to fetch quicklook by featureId {0} at controller layer.", featureId);

    final byte[] quickLookBy = featureService.getQuickLookByFeatureId(featureId);

    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.IMAGE_PNG);

    if (quickLookBy.length == 0) {
      return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(quickLookBy, headers, HttpStatus.OK);
  }
}
