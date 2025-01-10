package com.prueba.zara.infrastructure.adapter.entity;

import com.prueba.zara.utils.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
@DataJpaTest
class PriceEntityTest {

    @Test
    @DisplayName("Given constructor is used When creating entity Then all fields should be correctly set")
    void givenConstructorWhenCreatingEntityThenFieldsAreCorrectlySet() {
        // given
        Long id = TestUtils.id;
        Long brandId = TestUtils.brandId;
        Long productId = TestUtils.productId;
        Long priority = TestUtils.priority;
        Integer priceList = TestUtils.priceList;
        LocalDateTime startDate = TestUtils.startDate;
        LocalDateTime endDate = TestUtils.endDate;
        Double price = TestUtils.price;
        String currency = TestUtils.currency;

        // when
        PriceEntity priceEntity = new PriceEntity(id, brandId, productId, priority, priceList, startDate, endDate, price, currency);

        // then
        assertAll(
                () -> assertEquals(id, priceEntity.getId()),
                () -> assertEquals(brandId, priceEntity.getBrandId()),
                () -> assertEquals(productId, priceEntity.getProductId()),
                () -> assertEquals(priority, priceEntity.getPriority()),
                () -> assertEquals(priceList, priceEntity.getPriceList()),
                () -> assertEquals(startDate, priceEntity.getStartDate()),
                () -> assertEquals(endDate, priceEntity.getEndDate()),
                () -> assertEquals(price, priceEntity.getPrice()),
                () -> assertEquals(currency, priceEntity.getCurrency())
        );
    }

    @Test
    @DisplayName("Given default constructor When setting fields Then all getters should return the correct values")
    void givenDefaultConstructorWhenSettingFieldsThenGettersReturnCorrectValues() {
        // given
        PriceEntity priceEntity = new PriceEntity();

        // when
        priceEntity.setId(TestUtils.id);
        priceEntity.setBrandId(TestUtils.brandId);
        priceEntity.setProductId(TestUtils.productId);
        priceEntity.setPriority(TestUtils.priority);
        priceEntity.setPriceList(TestUtils.priceList);
        priceEntity.setStartDate(TestUtils.startDate);
        priceEntity.setEndDate(TestUtils.endDate);
        priceEntity.setPrice(TestUtils.price);
        priceEntity.setCurrency(TestUtils.currency);

        // then
        assertAll(
                () -> assertEquals(TestUtils.id, priceEntity.getId()),
                () -> assertEquals(TestUtils.brandId, priceEntity.getBrandId()),
                () -> assertEquals(TestUtils.productId, priceEntity.getProductId()),
                () -> assertEquals(TestUtils.priority, priceEntity.getPriority()),
                () -> assertEquals(TestUtils.priceList, priceEntity.getPriceList()),
                () -> assertEquals(TestUtils.startDate, priceEntity.getStartDate()),
                () -> assertEquals(TestUtils.endDate, priceEntity.getEndDate()),
                () -> assertEquals(TestUtils.price, priceEntity.getPrice()),
                () -> assertEquals(TestUtils.currency, priceEntity.getCurrency())
        );
    }

    @Test
    @DisplayName("Given default constructor When fields are not set Then fields should return null")
    void givenDefaultConstructorWhenFieldsAreNotSetThenFieldsReturnNull() {
        // given
        PriceEntity priceEntity = new PriceEntity();

        // then
        assertAll(
                () -> assertNull(priceEntity.getBrandId()),
                () -> assertNull(priceEntity.getProductId())
        );
    }

    @Test
    @DisplayName("Given extreme values When setting fields Then getters should return boundary values")
    void givenExtremeValuesWhenSettingFieldsThenGettersReturnBoundaryValues() {
        // given
        PriceEntity priceEntity = new PriceEntity();
        int maxPriceList = Integer.MAX_VALUE;
        long maxPriority = Long.MAX_VALUE;

        // when
        priceEntity.setPriceList(maxPriceList);
        priceEntity.setPriority(maxPriority);

        // then
        assertAll(
                () -> assertEquals(maxPriceList, priceEntity.getPriceList()),
                () -> assertEquals(maxPriority, priceEntity.getPriority())
        );
    }
}
