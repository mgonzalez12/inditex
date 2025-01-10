package com.prueba.zara.application.usecases;

import static com.prueba.zara.domain.constant.PriceConstant.PRICE_NOT_FOUND_MESSAGE_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.prueba.zara.infrastructure.adapter.exception.PriceNotFoundException;
import java.time.ZoneOffset;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.PriceDetails;

@ExtendWith(MockitoExtension.class)
class PriceServiceUnitTest {

    @Mock
    private PriceService priceServiceMock;

    private static final Long BRAND_ID = 1L;
    private static final Long PRODUCT_ID = 35455L;
    private static final LocalDateTime VALID_DATE = LocalDateTime.of(2020, 6, 14, 16, 0);
    private static PriceDetails mockedPriceDetails;
    private static final String EXCEPTION_MESSAGE_MISMATH = "Exception message mismatch";
    private static final String PRODUCT_ID_CANNOT_BE_NULL = "Product ID cannot be null";
    private static final String BRAND_ID_CANNOT_BE_NULL = "Brand ID cannot be null";
    private static final String PRICE_ID_CANNOT_BE_NULL = "Price cannot be null";
    private static final String MESSAGE_BRAND_ID = "BrandId mismatch";
    private static final String MESSAGE_PRODUCT_ID = "ProductId mismatch";
    private static final String MESSAGE_PRICE = "Price mismatch";
    private static final String MESSAGE_STARTDATE = "StartDate mismatch";
    private static final String MESSAGE_PRICE_DETAILS = "PriceDetails should not be null";

    @BeforeEach
    void setUp() {
        // Inicializar datos de prueba
        mockedPriceDetails = new PriceDetails();
        mockedPriceDetails.setBrandId(BRAND_ID);
        mockedPriceDetails.setProductId(PRODUCT_ID);
        mockedPriceDetails.setPrice(38.95);

        // Convertir VALID_DATE a OffsetDateTime con zona horaria UTC
        mockedPriceDetails.setStartDate(VALID_DATE.atOffset(ZoneOffset.UTC));
    }

    @Test
    @DisplayName("Should return PriceDetails when a valid request is made")
    void shouldReturnPriceDetails_WhenValidRequestIsMade() {
        // Arrange
        when(priceServiceMock.getPriceForProductAndBrand(BRAND_ID, PRODUCT_ID, VALID_DATE))
                .thenReturn(mockedPriceDetails);

        // Act
        PriceDetails actualPriceDetails = priceServiceMock.getPriceForProductAndBrand(BRAND_ID, PRODUCT_ID, VALID_DATE);

        // Assert
        assertNotNull(actualPriceDetails, MESSAGE_PRICE_DETAILS);
        assertEquals(mockedPriceDetails.getBrandId(), actualPriceDetails.getBrandId(), MESSAGE_BRAND_ID);
        assertEquals(mockedPriceDetails.getProductId(), actualPriceDetails.getProductId(), MESSAGE_PRODUCT_ID);
        assertEquals(mockedPriceDetails.getPrice(), actualPriceDetails.getPrice(), MESSAGE_PRICE);
        assertEquals(mockedPriceDetails.getStartDate(), actualPriceDetails.getStartDate(), MESSAGE_STARTDATE);
    }

    @Test
    @DisplayName("Should throw NullPointerException when PriceDetails is null")
    void shouldThrowException_WhenPriceDetailsIsNull() {
        // Arrange
        when(priceServiceMock.getPriceForProductAndBrand(any(), any(), any()))
                .thenThrow(new NullPointerException(PRICE_ID_CANNOT_BE_NULL));

        // Act & Assert
        NullPointerException exception = assertThrows(NullPointerException.class, () ->
                priceServiceMock.getPriceForProductAndBrand(BRAND_ID, PRODUCT_ID, VALID_DATE));
        assertEquals(PRICE_ID_CANNOT_BE_NULL, exception.getMessage(), EXCEPTION_MESSAGE_MISMATH);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when brandId is null")
    void shouldThrowException_WhenBrandIdIsNull() {
        // Arrange
        when(priceServiceMock.getPriceForProductAndBrand(null, PRODUCT_ID, VALID_DATE))
                .thenThrow(new IllegalArgumentException(BRAND_ID_CANNOT_BE_NULL));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                priceServiceMock.getPriceForProductAndBrand(null, PRODUCT_ID, VALID_DATE));
        assertEquals(BRAND_ID_CANNOT_BE_NULL, exception.getMessage(), EXCEPTION_MESSAGE_MISMATH);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when productId is null")
    void shouldThrowException_WhenProductIdIsNull() {
        // Arrange
        when(priceServiceMock.getPriceForProductAndBrand(BRAND_ID, null, VALID_DATE))
                .thenThrow(new IllegalArgumentException(PRODUCT_ID_CANNOT_BE_NULL));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                priceServiceMock.getPriceForProductAndBrand(BRAND_ID, null, VALID_DATE));
        assertEquals(PRODUCT_ID_CANNOT_BE_NULL, exception.getMessage(), EXCEPTION_MESSAGE_MISMATH);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when date is in the past")
    void shouldThrowException_WhenDateIsInThePast() {
        // Arrange
        LocalDateTime pastDate = LocalDateTime.of(2020, 6, 13, 10, 0);

        // Simulate service behavior for past dates
        when(priceServiceMock.getPriceForProductAndBrand(BRAND_ID, PRODUCT_ID, pastDate))
                .thenThrow(new PriceNotFoundException(PRICE_NOT_FOUND_MESSAGE_ERROR));

        // Act & Assert
        PriceNotFoundException exception = assertThrows(PriceNotFoundException.class, () ->
                priceServiceMock.getPriceForProductAndBrand(BRAND_ID, PRODUCT_ID, pastDate));

        assertEquals(PRICE_NOT_FOUND_MESSAGE_ERROR, exception.getMessage(), EXCEPTION_MESSAGE_MISMATH);
    }
}
