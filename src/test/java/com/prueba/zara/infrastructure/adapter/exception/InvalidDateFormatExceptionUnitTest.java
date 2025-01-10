package com.prueba.zara.infrastructure.adapter.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InvalidDateFormatExceptionUnitTest {

    public static final String INVALID_DATE_FORMAT_MESSAGE = "Invalid date format";
    @Test
    @DisplayName("Given an Invalid Date Format When the exception is thrown Then it should return the correct message")
    void GivenAnInvalidDateFormatWhenTheExceptionIsThrownThenItShouldReturnTheCorrectMessage() {
            InvalidDateFormatException exception = new InvalidDateFormatException(INVALID_DATE_FORMAT_MESSAGE);
            String actualMessage = exception.getMessage();
            assertEquals(INVALID_DATE_FORMAT_MESSAGE, actualMessage);
        }
}