package com.prueba.zara.application.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.prueba.zara.application.mapper.PriceDtoMapper;
import com.prueba.zara.infrastructure.adapter.entity.PriceEntity;
import com.prueba.zara.infrastructure.adapter.exception.PriceNotFoundException;
import com.prueba.zara.infrastructure.adapter.repository.PriceRepository;
import java.time.LocalDateTime;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.PriceDetails;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplUnitTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceDtoMapper priceMapper;

    @InjectMocks
    private PriceServiceImpl priceService;

    private static final Long VALID_BRAND_ID = 1L;
    private static final Long VALID_PRODUCT_ID = 35455L;
    private static final LocalDateTime VALID_DATE = LocalDateTime.of(2020, 6, 14, 10, 0);
    private static final LocalDateTime PAST_DATE = LocalDateTime.of(2020, 6, 13, 10, 0);
    private static final String PRICE_NOT_FOUND_MESSAGE_ERROR = "Precio no encontrado para los parámetros dados.";
    private static final String PRODUCT_ID_CANNOT_BE_NULL = "productId no puede ser null";
    private static final String BRAND_ID_CANNOT_BE_NULL = "brandId no puede ser null";
    private static final String MESSAGE_RESULT_NOT_BE_NULL = "Result should not be null";
    private static final String MESSAGE_RESULT_MATH_THE_MOCK_DETAILS = "Result should match the mock details";
    private static final String EXCEPTION_MESSAGE = "Exception message should match";


    @Test
    @DisplayName("Should return PriceDetails when a valid request is made - status 200")
    void getPriceForProductAndBrand_PriceFound_ReturnsPriceDetails() {
        // Arrange
        PriceEntity mockPriceEntity = new PriceEntity();
        PriceDetails mockPriceDetails = new PriceDetails();
        Mockito.when(priceRepository.findAllByBrandIdAndProductIdAndStartDateOrderByPriorityDescPriceDesc(
                        VALID_BRAND_ID, VALID_PRODUCT_ID, VALID_DATE))
                .thenReturn(Collections.singletonList(mockPriceEntity));
        Mockito.when(priceMapper.toPriceDetails(mockPriceEntity)).thenReturn(mockPriceDetails);

        // Act
        PriceDetails result = priceService.getPriceForProductAndBrand(VALID_BRAND_ID, VALID_PRODUCT_ID, VALID_DATE);

        // Assert
        assertAll(
                () -> assertNotNull(result, MESSAGE_RESULT_NOT_BE_NULL),
                () -> assertEquals(mockPriceDetails, result, MESSAGE_RESULT_MATH_THE_MOCK_DETAILS)
        );
    }

    @Test
    @DisplayName("Should throw PriceNotFoundException when no prices are found")
    void getPriceForProductAndBrand_PriceNotFound_ThrowsException() {
        // Arrange
        Mockito.when(priceRepository.findAllByBrandIdAndProductIdAndStartDateOrderByPriorityDescPriceDesc(
                        VALID_BRAND_ID, VALID_PRODUCT_ID, VALID_DATE))
                .thenReturn(Collections.emptyList());

        // Act & Assert
        PriceNotFoundException exception = assertThrows(PriceNotFoundException.class, () ->
                priceService.getPriceForProductAndBrand(VALID_BRAND_ID, VALID_PRODUCT_ID, VALID_DATE));
        assertEquals(PRICE_NOT_FOUND_MESSAGE_ERROR, exception.getMessage(), EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when date is in the past")
    void getPriceForProductAndBrand_DateInThePast_ThrowsException() {
        // Arrange
        Mockito.when(priceRepository.findAllByBrandIdAndProductIdAndStartDateOrderByPriorityDescPriceDesc(
                        VALID_BRAND_ID, VALID_PRODUCT_ID, PAST_DATE))
                .thenThrow(new IllegalArgumentException(PRICE_NOT_FOUND_MESSAGE_ERROR));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                priceService.getPriceForProductAndBrand(VALID_BRAND_ID, VALID_PRODUCT_ID, PAST_DATE));
        assertEquals(PRICE_NOT_FOUND_MESSAGE_ERROR, exception.getMessage(), EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when brandId is null")
    void getPriceForProductAndBrand_BrandIdIsNull_ThrowsException() {
        // Arrange
        Mockito.doThrow(new IllegalArgumentException(BRAND_ID_CANNOT_BE_NULL))
                .when(priceRepository)
                .findAllByBrandIdAndProductIdAndStartDateOrderByPriorityDescPriceDesc(
                        Mockito.isNull(),
                        Mockito.anyLong(),
                        Mockito.any(LocalDateTime.class));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                priceService.getPriceForProductAndBrand(null, VALID_PRODUCT_ID, VALID_DATE));
        assertEquals(BRAND_ID_CANNOT_BE_NULL, exception.getMessage(), EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when productId is null")
    void getPriceForProductAndBrand_ProductIdIsNull_ThrowsException() {
        // Arrange
        Mockito.doThrow(new IllegalArgumentException(PRODUCT_ID_CANNOT_BE_NULL))
                .when(priceRepository)
                .findAllByBrandIdAndProductIdAndStartDateOrderByPriorityDescPriceDesc(
                        Mockito.anyLong(),
                        Mockito.isNull(),
                        Mockito.any(LocalDateTime.class));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                priceService.getPriceForProductAndBrand(VALID_BRAND_ID, null, VALID_DATE));
        assertEquals(PRODUCT_ID_CANNOT_BE_NULL, exception.getMessage(), EXCEPTION_MESSAGE);
    }
}

