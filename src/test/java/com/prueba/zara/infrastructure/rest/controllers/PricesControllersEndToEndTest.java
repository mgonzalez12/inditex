package com.prueba.zara.infrastructure.rest.controllers;


import com.prueba.zara.utils.TestUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static com.prueba.zara.utils.JsonFile.loadJsonFromFile;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PricesControllersEndToEndTest {

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    private final String baseUrl = "http://localhost:8081/api";


    @Test
    @SneakyThrows
    @DisplayName("Test funcional status 200")
    void testValidRequest_Returns200() {
        String url = baseUrl + "/prices" + "?brandId=" + TestUtils.brandId
                + "&productId=" + TestUtils.productId + "&date=" + TestUtils.formattedDate;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String expectedJson = loadJsonFromFile("testControllerResponse200.json");

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expectedJson, response.getBody())
        );
    }


    @Test
    @DisplayName("Test funcional status 404")
    void testInvalidDateFormat_Returns400() {
        String invalidDate = "invalid-date";
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/prices")
                .queryParam("brandId", TestUtils.brandId)
                .queryParam("productId", TestUtils.productId)
                .queryParam("date", invalidDate)
                .toUriString();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}
