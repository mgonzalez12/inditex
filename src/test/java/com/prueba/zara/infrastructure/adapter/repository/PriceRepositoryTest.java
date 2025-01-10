package com.prueba.zara.infrastructure.adapter.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.prueba.zara.infrastructure.adapter.entity.PriceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
class PriceRepositoryTest {

    @Autowired
    PriceRepository priceRepository;

    public static final Long BRAND_ID = 1L;
    public static final Long PRODUCT_ID = 1L;
    public static final LocalDateTime START_DATE = LocalDateTime.now();

    @Test
    @DisplayName("Given a valid BrandId, ProductId, and StartDate, When finding prices, Then it should return an empty list if no data matches")
    @Sql("/data-test.sql")
    void GivenAValidBrandIdProductIdAndStartDateWhenFindingPricesThenItShouldReturnAnEmptyListIfNoDataMatches() {
        // When
        List<PriceEntity> result = priceRepository.findAllByBrandIdAndProductIdAndStartDateOrderByPriorityDescPriceDesc(
                    BRAND_ID, PRODUCT_ID, START_DATE);
        // Then
        assertEquals(0, result.size());
    }


}