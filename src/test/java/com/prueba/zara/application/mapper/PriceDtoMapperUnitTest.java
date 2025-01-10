package com.prueba.zara.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.prueba.zara.infrastructure.adapter.entity.PriceEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.PriceDetails;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import static org.assertj.core.api.Assertions.assertThat;

class PriceDtoMapperUnitTest {

    private final PriceDtoMapper mapper = Mappers.getMapper(PriceDtoMapper.class);

    @Test
    @DisplayName("Given To Price Details")
    void givenToPriceDetails() {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setBrandId(1L);
        priceEntity.setProductId(2L);
        priceEntity.setPriceList(3);
        priceEntity.setStartDate(LocalDateTime.of(2024, 2, 13, 10, 0));
        priceEntity.setPrice(100.0);
        PriceDetails priceDetails = mapper.toPriceDetails(priceEntity);
        assertThat(priceDetails.getBrandId()).isEqualTo(priceEntity.getBrandId());
        assertThat(priceDetails.getProductId()).isEqualTo(priceEntity.getProductId());
        assertThat(priceDetails.getPriceList()).isEqualTo(priceEntity.getPriceList());
        assertThat(priceDetails.getStartDate()).isEqualTo(OffsetDateTime.of(priceEntity.getStartDate(), ZoneOffset.UTC));
        assertThat(priceDetails.getPrice()).isEqualTo(priceEntity.getPrice());
    }

    @Test
    @DisplayName("Given To OffsetDateTime")
    void givenToOffsetDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 2, 13, 10, 0);
        OffsetDateTime offsetDateTime = mapper.toOffsetDateTime(localDateTime);
        assertThat(offsetDateTime).isEqualTo(OffsetDateTime.of(localDateTime, ZoneOffset.UTC));
    }

    @Test
    @DisplayName("Given To OffsetDateTime With Null Input")
    void givenToOffsetDateTimeWithNullInput() {
        OffsetDateTime offsetDateTime = mapper.toOffsetDateTime(null);
        assertNull(offsetDateTime);
    }

    @Test
    @DisplayName("Given Map With Null Input")
    void givenMapWithNullInput() {
        OffsetDateTime offsetDateTime = mapper.map(null);
        assertNull(offsetDateTime);
    }

}
