package com.prueba.zara.application.mapper;

import com.prueba.zara.infrastructure.adapter.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.openapitools.model.PriceDetails;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface PriceDtoMapper {

    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "priceList", target = "priceList")
    @Mapping(source = "startDate", target = "startDate", qualifiedByName = "toOffsetDateTime")
    @Mapping(source = "price", target = "price")
    PriceDetails toPriceDetails(PriceEntity priceEntity);

    @Named("toOffsetDateTime")
    default OffsetDateTime toOffsetDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.atOffset(ZoneOffset.UTC);
    }

    default OffsetDateTime map(LocalDateTime value) {
        return toOffsetDateTime(value);
    }
}
