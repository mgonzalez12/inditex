package com.prueba.zara.utils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class TestUtils {
    public static final Long brandId = 1L;
    public static final Long productId = 35455L;
    public static final String formattedDate = "2020-06-14T00:00:00Z";
    public static final OffsetDateTime offsetDateTime = OffsetDateTime.parse(formattedDate);
    public static final LocalDateTime startDate = LocalDateTime.now();
    public static final  LocalDateTime endDate = startDate.plusDays(7);
    public static final  Double price = 35.5;
    public static final  String currency = "EUR";

    public static final Long priority = 1L;
    public static final Integer priceList = 1;

    public static final Long id = 1L;
}

