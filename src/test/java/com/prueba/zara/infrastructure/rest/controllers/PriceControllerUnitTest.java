package com.prueba.zara.infrastructure.rest.controllers;

import com.prueba.zara.application.usecases.PriceService;
import com.prueba.zara.domain.constant.PriceConstant;
import com.prueba.zara.infrastructure.adapter.exception.PriceNotFoundException;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openapitools.model.PriceDetails;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest
class PriceControllerUnitTest {

     public static final String BAD_REQUEST_MESSAGE = "Expected 400 BAD_REQUEST status code";

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PricesControllers pricesControllers;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pricesControllers = new PricesControllers(priceService);
    }

    @Test
    @DisplayName("Given valid input When request is made Then returns status 200 with PriceDetails")
    void getPriceForProductAndBrand_PriceFound_Returns200() {
        // given
        Long brandId = 1L;
        Long productId = 1L;
        OffsetDateTime date = OffsetDateTime.now();
        PriceDetails mockedDTO = new PriceDetails();
        // when
        Mockito.when(priceService.getPriceForProductAndBrand(brandId, productId, date.toLocalDateTime())).thenReturn(mockedDTO);
        ResponseEntity<PriceDetails> response = pricesControllers.pricesGet(brandId, productId, date);
        // then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Given price not found When request is made Then returns status 404 with exception")
    void getPriceForProductAndBrand_PriceNotFound_Returns404() {
        Long brandId = 1L;
        Long productId = 91233L;
        OffsetDateTime date = OffsetDateTime.now();

        Mockito.when(priceService.getPriceForProductAndBrand(brandId, productId, date.toLocalDateTime()))
                .thenThrow(new PriceNotFoundException(PriceConstant.PRICE_NOT_FOUND_MESSAGE_ERROR));

        Assertions.assertThrows(PriceNotFoundException.class, () -> {
            pricesControllers.pricesGet(brandId, productId, date);
        });
    }


    @Test
    @DisplayName("Given invalid input (null values) When request is made Then returns status 400 Bad Request")
    void getPriceForProductAndBrand_BadRequest_Returns400() {
        // given
        Long brandId = null;  // Invalid brandId
        Long productId = null; // Invalid productId
        OffsetDateTime date = OffsetDateTime.now();

        try {
            // when
            ResponseEntity<PriceDetails> response = pricesControllers.pricesGet(brandId, productId, date);

            // then
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), BAD_REQUEST_MESSAGE);
        } catch (IllegalArgumentException ex) {
            // Catching the IllegalArgumentException and checking if it's handled by the GlobalExceptionHandler
            Assertions.assertTrue(ex.getMessage().contains("Los campos 'brandId', 'productId' y 'date' no deben ser nulos."));
        }
    }


}