package com.prueba.zara.application.usecases;

import org.openapitools.model.PriceDetails;

import java.time.LocalDateTime;

public interface PriceService {
    PriceDetails getPriceForProductAndBrand(Long brandId, Long productId, LocalDateTime startDate);
}
