package com.prueba.zara.infrastructure.rest.controllers;

import com.prueba.zara.application.usecases.PriceService;
import org.openapitools.api.PricesApi;
import org.openapitools.model.PriceDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.OffsetDateTime;

import java.util.Optional;


@RestController
@RequestMapping("/api")
public class PricesControllers implements PricesApi {

    private final PriceService priceService;

    public PricesControllers(PriceService priceService) {
        this.priceService = priceService;
    }
    @Override
    public ResponseEntity<PriceDetails> pricesGet(Long brandId, Long productId, OffsetDateTime date) {
        validateParameters(brandId, productId, date);
        return Optional.ofNullable(priceService.getPriceForProductAndBrand(brandId, productId, date.toLocalDateTime()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
        }

    private void validateParameters(Long brandId, Long productId, OffsetDateTime date) {
        // Verifica si alguno de los parámetros es nulo y lanza excepción
        if (brandId == null || productId == null || date == null) {
            throw new IllegalArgumentException("Los campos 'brandId', 'productId' y 'date' no deben ser nulos.");
        }
    }
}
