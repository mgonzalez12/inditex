package com.prueba.zara.infrastructure.rest.advice;

import com.prueba.zara.infrastructure.adapter.exception.PriceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
class GlobalExceptionHandlerUnitTest {

    public static final String INVALID_FORMAT = "Invalid format";
    public static final String INVALID_FORMAT_DATE = "Invalid date format";
    public static final String PRICE_NOT_FOUND = "Price not found";
    public static final String GENERIC_EXCEPTION ="Generic exception";
    public static final String MESSAGE_INTERNAL_SERVER_ERROR = "Internal server error.";
    public static final String VALOR = "valor";
    public static final String PARAM = "param1";
    public static final String MESSAGE_PARAM = "Parametro 'param1' debe ser de tipo 'Integer'.";

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    @DisplayName("Given a PriceNotFoundException, When handling the exception, Then it should return a 404 Not Found response with the correct message")
    void givenPriceNotFoundException_WhenHandlingTheException_ThenReturn404NotFoundResponseWithCorrectMessage() {
        // Given
        PriceNotFoundException priceNotFoundException = new PriceNotFoundException(PRICE_NOT_FOUND);

        // When
        ResponseEntity<Object> response = exceptionHandler.handlePriceNotFoundException(priceNotFoundException);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(PRICE_NOT_FOUND, response.getBody());
    }

    @Test
    @DisplayName("Given a DateTimeParseException, When handling the exception, Then it should return a 400 Bad Request response with the correct message")
    void givenDateTimeParseException_WhenHandlingTheException_ThenReturn400BadRequestResponseWithCorrectMessage() {
        // Given
        DateTimeParseException dateTimeParseException = new DateTimeParseException(INVALID_FORMAT, "", 0);

        // When
        ResponseEntity<Object> response = exceptionHandler.handleDateExceptions(dateTimeParseException);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(INVALID_FORMAT_DATE, response.getBody());
    }

    @Test
    @DisplayName("Given a Generic Exception, When handling the exception, Then it should return a 500 Internal Server Error response with the correct message")
    void givenGenericException_WhenHandlingTheException_ThenReturn500InternalServerErrorResponseWithCorrectMessage() {
        // Given
        Exception exception = new Exception(GENERIC_EXCEPTION);

        // When
        ResponseEntity<Object> response = exceptionHandler.handleGenericException(exception);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(MESSAGE_INTERNAL_SERVER_ERROR, response.getBody());
    }

    @Test
    @DisplayName("Given a MethodArgumentTypeMismatchException, When handling the exception, Then it should return a 400 Bad Request response with the correct message")
    void givenMethodArgumentTypeMismatchException_WhenHandlingTheException_ThenReturn400BadRequestResponseWithCorrectMessage() {
        // Given
        MethodArgumentTypeMismatchException ex = new MethodArgumentTypeMismatchException(VALOR, Integer.class, PARAM, null, null);

        // When
        ResponseEntity<Object> response = exceptionHandler.handleMethodArgumentTypeMismatch(ex);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(MESSAGE_PARAM, response.getBody());
    }
}
