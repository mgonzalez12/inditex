package com.prueba.zara.infrastructure.rest.advice;

import com.prueba.zara.infrastructure.adapter.exception.InvalidDateFormatException;
import com.prueba.zara.infrastructure.adapter.exception.PriceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    public static final String INTERNAL_SERVER_ERROR_MESSAGE ="Internal server error.";
    public static final String BAD_REQUEST_MESSAGE = "Invalid date format";

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", HttpStatus.BAD_REQUEST.value());
        responseBody.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<Object> handlePriceNotFoundException(PriceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler({ DateTimeParseException.class, InvalidDateFormatException.class })
    public ResponseEntity<Object> handleDateExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST_MESSAGE);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String paramName = ex.getName();
        Class<?> paramType = ex.getRequiredType();
        String errorMessage = "Parametro '" + paramName + "' debe ser de tipo '" + (paramType != null ? paramType.getSimpleName() : "desconocido") + "'.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        if (ex instanceof IllegalArgumentException) {
            return handleIllegalArgumentException((IllegalArgumentException) ex);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(INTERNAL_SERVER_ERROR_MESSAGE);
    }

}
