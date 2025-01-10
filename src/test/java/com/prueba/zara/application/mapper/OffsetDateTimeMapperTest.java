package com.prueba.zara.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OffsetDateTimeMapperTest {

    private final OffsetDateTimeMapper mapper = new OffsetDateTimeMapper();

    @Test
    @DisplayName("Prueba con una fecha no nula")
    void testToOffsetDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 2, 13, 10, 0);
        OffsetDateTime expectedOffsetDateTime = OffsetDateTime.of(localDateTime, ZoneOffset.UTC);
        OffsetDateTime actualOffsetDateTime = mapper.toOffsetDateTime(localDateTime);
        assertEquals(expectedOffsetDateTime, actualOffsetDateTime);
        assertNull(mapper.toOffsetDateTime(null));
    }
}
