package com.up42.backendcodingchallenge;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest {
  @LocalServerPort
  int port;

  @BeforeEach
  public void setUp() {
    RestAssured.port = port;
  }

  @Test
  void shouldReturnAllFeatures() {
    //Given
    List<ExpectedFeature> expectedFeatures = getExpectedFeatures();

    //When
    ValidatableResponse validatableResponse = RestAssured.given()
        .get("/features")
        .then()
        .statusCode(200);

    //Then
    expectedFeatures
        .forEach(expectedFeature -> validatableResponse
            .body(String.format("id[%d]", expectedFeature.getKey()), equalTo(expectedFeature.getId()))
            .body(String.format("timestamp[%d]", expectedFeature.getKey()), equalTo(expectedFeature.getTimestamp()))
            .body(String.format("beginViewingDate[%d]", expectedFeature.getKey()), equalTo(expectedFeature.getBeginViewingDate()))
            .body(String.format("endViewingDate[%d]", expectedFeature.getKey()), equalTo(expectedFeature.getEndViewingDate()))
            .body(String.format("missionName[%d]", expectedFeature.getKey()), equalTo(expectedFeature.getMissionName())));
  }

  @NotNull
  private List<ExpectedFeature> getExpectedFeatures() {
    return Arrays.asList(
        ExpectedFeature.builder()
            .key(0)
            .id("39c2f29e-c0f8-4a39-a98b-deed547d6aea")
            .timestamp(1554831167697L)
            .beginViewingDate(1554831167697L)
            .endViewingDate(1554831202043L)
            .missionName("Sentinel-1B")
            .build(),
        ExpectedFeature.builder()
            .key(1)
            .id("cf5dbe37-ab95-4af1-97ad-2637aec4ddf0")
            .timestamp(1556904743783L)
            .beginViewingDate(1556904743783L)
            .endViewingDate(1556904768781L)
            .missionName("Sentinel-1B")
            .build(),
        ExpectedFeature.builder()
            .key(2)
            .id("ca81d759-0b8c-4b3f-a00a-0908a3ddd655")
            .timestamp(1558155123786L)
            .beginViewingDate(1558155123786L)
            .endViewingDate(1558155148785L)
            .missionName("Sentinel-1A")
            .build(),
        ExpectedFeature.builder()
            .key(3)
            .id("0b598c52-7bf2-4df0-9d09-94229cdfbc0b")
            .timestamp(1560661222337L)
            .beginViewingDate(1560661222337L)
            .endViewingDate(1560661247336L)
            .missionName("Sentinel-1A")
            .build(),
        ExpectedFeature.builder()
            .key(4)
            .id("aeaa71d6-c549-4620-99ce-f8cae750b8d5")
            .timestamp(1560015145495L)
            .beginViewingDate(1560015145495L)
            .endViewingDate(1560015170493L)
            .missionName("Sentinel-1B")
            .build(),
        ExpectedFeature.builder()
            .key(5)
            .id("12d0b505-2c70-4420-855c-936d05c55669")
            .timestamp(1555477219508L)
            .beginViewingDate(1555477219508L)
            .endViewingDate(1555477244506L)
            .missionName("Sentinel-1A")
            .build(),
        ExpectedFeature.builder()
            .key(6)
            .id("7f23a853-76a8-4787-a2ba-fdfe93e74165")
            .timestamp(1561051946263L)
            .beginViewingDate(1561051946263L)
            .endViewingDate(1561051971261L)
            .missionName("Sentinel-1B")
            .build(),
        ExpectedFeature.builder()
            .key(7)
            .id("b3ac34e1-12e6-4dee-9e21-b717f472fcfd")
            .timestamp(1557941519219L)
            .beginViewingDate(1557941519219L)
            .endViewingDate(1557941544218L)
            .missionName("Sentinel-1B")
            .build(),
        ExpectedFeature.builder()
            .key(8)
            .id("6df9b09a-3a93-4064-bf9f-47e5809b0ffe")
            .timestamp(1557118373086L)
            .beginViewingDate(1557118373086L)
            .endViewingDate(1557118398085L)
            .missionName("Sentinel-1A")
            .build(),
        ExpectedFeature.builder()
            .key(9)
            .id("2ed68fe5-f719-48c3-aa27-b0cc155f06cb")
            .timestamp(1560015170494L)
            .beginViewingDate(1560015170494L)
            .endViewingDate(1560015204843L)
            .missionName("Sentinel-1B")
            .build(),
        ExpectedFeature.builder()
            .key(10)
            .id("f556abfe-5558-4d3a-9849-c0de4d2766fd")
            .timestamp(1561051971263L)
            .beginViewingDate(1561051971263L)
            .endViewingDate(1561052005606L)
            .missionName("Sentinel-1B")
            .build(),
        ExpectedFeature.builder()
            .key(11)
            .id("63fded50-842f-4384-ac65-e83d584beb4c")
            .timestamp(1556904768782L)
            .beginViewingDate(1556904768782L)
            .endViewingDate(1556904803124L)
            .missionName("Sentinel-1B")
            .build(),
        ExpectedFeature.builder()
            .key(12)
            .id("b0d3bf6a-ff54-49e0-a4cb-e57dcb68d3b5")
            .timestamp(1558155148786L)
            .beginViewingDate(1558155148786L)
            .endViewingDate(1558155173785L)
            .missionName("Sentinel-1A")
            .build(),
        ExpectedFeature.builder()
            .key(13)
            .id("08a190bf-8c7e-4e94-a22c-7f3be11f642c")
            .timestamp(1555044772083L)
            .beginViewingDate(1555044772083L)
            .endViewingDate(1555044797082L)
            .missionName("Sentinel-1A")
            .build());
  }

  @Builder
  @Getter
  private static class ExpectedFeature {
    private final int key;
    private final String id;
    private final Long timestamp;
    private final Long beginViewingDate;
    private final Long endViewingDate;
    private final String missionName;
  }
}
