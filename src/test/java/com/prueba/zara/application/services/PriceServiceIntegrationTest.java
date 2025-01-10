package com.prueba.zara.application.services;

import com.prueba.zara.application.usecases.PriceService;
import com.prueba.zara.infrastructure.adapter.entity.PriceEntity;
import com.prueba.zara.infrastructure.adapter.exception.PriceNotFoundException;
import com.prueba.zara.infrastructure.adapter.repository.PriceRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openapitools.model.PriceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PriceServiceIntegrationTest {

    @Autowired
    PriceService priceService;

    @Autowired
    PriceRepository priceRepository;

    @Test
    @DisplayName("Given valid price data When price is saved Then it can be retrieved successfully")
    void saveAndRetrievePrice() {
        // Given
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setBrandId(1L);
        priceEntity.setProductId(1L);
        priceEntity.setPrice(100.00);
        priceEntity.setCurrency("USD");
        priceEntity.setStartDate(LocalDateTime.now());
        priceEntity.setEndDate(LocalDateTime.now().plusDays(10));
        priceRepository.save(priceEntity);

        // When
        PriceDetails retrievedPrice = priceService.getPriceForProductAndBrand(priceEntity.getBrandId(),
                priceEntity.getProductId(), priceEntity.getStartDate());
        // Then
        assertNotNull(retrievedPrice);
        assertEquals(priceEntity.getPrice(), retrievedPrice.getPrice());
    }

    @Test
    @DisplayName("Given invalid brandId or productId When retrieving price Then throws PriceNotFoundException")
    void getPriceForProductAndBrand_InvalidId_ThrowsPriceNotFoundException() {
        // Given
        Long invalidBrandId = 999L;
        Long invalidProductId = 999L;
        LocalDateTime date = LocalDateTime.now();

        // When & Then
        assertThrows(PriceNotFoundException.class, () -> {
            priceService.getPriceForProductAndBrand(invalidBrandId, invalidProductId, date);
        });
    }
}
