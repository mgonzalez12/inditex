package com.prueba.zara.domain.constant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PriceConstantTest {

    public static final String ESPECTED_ERROR_MESSAGE = "Precio no encontrado para los parámetros dados.";
    public static final String ESPECTED_INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error.";
    @Test
    @DisplayName("Given PriceConstant When Price Not Found Error Message is requested Then returns correct error message")
    void givenPriceConstantWhenPriceNotFoundErrorMessageIsRequestedThenReturnsCorrectErrorMessage() {
        assertEquals(PriceConstant.PRICE_NOT_FOUND_MESSAGE_ERROR, ESPECTED_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("Given PriceConstant When Internal Server Error Message is requested Then returns correct error message")
    void givenPriceConstantWhenInternalServerErrorMessageIsRequestedThenReturnsCorrectErrorMessage() {
        assertEquals(PriceConstant.PRICE_MESSAGE_INTERNAL_SERVER_ERROR, ESPECTED_INTERNAL_SERVER_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("Given PriceConstant When Get Instance is called Then returns a non-null instance")
    void givenPriceConstantWhenGetInstanceIsCalledThenReturnsANonNullInstance() {
        PriceConstant instance = PriceConstant.getInstance();
        assertNotNull(instance); // Verifica que la instancia devuelta no sea nula
    }

    @Test
    @DisplayName("Given PriceConstant When Private Constructor is accessed Then returns a non-null instance")
    void givenPriceConstantWhenPrivateConstructorIsAccessedThenReturnsANonNullInstance() {
        PriceConstant instance = PriceConstant.getInstance();
        assertNotNull(instance);
    }
}